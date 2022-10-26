package com.mygdx.game;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.colecciones.ColeccionAsteroides;
import com.mygdx.game.colecciones.ColeccionBalas;
import com.mygdx.game.colecciones.ColeccionConsumibles;
import com.mygdx.game.colecciones.ColeccionHirientes;

public class PantallaJuego implements Screen {
	private SpaceNav game;
	private OrthographicCamera camera;	
	private SpriteBatch batch;
	private Texture font;
	private Sound explosionSound;
	private Music gameMusic;
	private int score;
	private int ronda;
	private int velAsteroides; 
	private int cantAsteroides;
	
	private Nave nave;
	private ColeccionAsteroides asteroides;
	private ColeccionBalas balas;
	private ColeccionConsumibles consumibles;
	private ColeccionHirientes hirientes;

	public PantallaJuego(SpaceNav game, int ronda, int vidas, int score,  
			int velAsteroides, int cantAsteroides) {
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		this.velAsteroides = velAsteroides;
		this.cantAsteroides = cantAsteroides;
		
		batch = game.getBatch();
		camera = new OrthographicCamera();	
		Util.setOtrhoCam(camera);
		//inicializar assets; musica de fondo y efectos de sonido
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.5f);
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav")); //
		font = new Texture(Gdx.files.internal("FondoGame.png"));
		
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.5f);
		gameMusic.play();
		
	    // cargar imagen de la nave, 64x64   
	    nave = new Nave(
	    		Gdx.graphics.getWidth()/2-50,
	    		30,
	    		Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"))
	    		);
        
           
        asteroides = new ColeccionAsteroides();
        consumibles = new ColeccionConsumibles();
        hirientes = new ColeccionHirientes();
        balas = new ColeccionBalas();
        
        asteroides.crear(cantAsteroides, velAsteroides);
	}
    
	public void dibujarEncabezado() {
		CharSequence str = "Vidas: " + nave.getVidas() + " Ronda: " + ronda;
		game.getFont().getData().setScale(2f);		
		game.getFont().draw(batch, str, 10, 30);
		game.getFont().draw(batch, "Score:" + this.score, Gdx.graphics.getWidth()-150, 30);
		game.getFont().draw(batch, "HighScore:" + game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(font, 0, 0);
		dibujarEncabezado();
		
	    if (!nave.estaHerida()) {
	    	Iterator<DamageNave> iteratorAsteroides = asteroides.getAsteroides();
	    	while(iteratorAsteroides.hasNext()) {
	    		DamageNave a = iteratorAsteroides.next();
	    		if(balas.verificarColisiones(a)) {
	    			iteratorAsteroides.remove();
	    			asteroides.eliminar(a);
	    			consumibles.generar(a.getX(), a.getY(), a.getVelocidadX(), a.getVelocidadY());
	    			agregarPuntaje(10);
	    		}
	    	}
	    	
	    	if (asteroides.getCantidad() < 10 && hirientes.estaVacia()) {
				hirientes.generar();
			}
	    	
	    	consumibles.verificarColisiones(nave);
			
	    	asteroides.verificarColisiones();
	    	hirientes.verificarColisiones();
	    	
	    	if (nave.disparar()) {
	    		Bala bala = nave.generarBala();
	    		balas.agregar(bala);
	    	}
	    }
	    
	    asteroides.verificarColisiones(nave);
	    hirientes.verificarColisiones(nave);
	    
	    asteroides.actualizar();
	    hirientes.actualizar();
	    consumibles.actualizar();
	    balas.actualizar();
	    nave.actualizar();
	    
	    asteroides.dibujar(batch);
	    hirientes.dibujar(batch);
	    consumibles.dibujar(batch);
	    balas.dibujar(batch);
	    nave.dibujar(batch);
	      
	    if (nave.estaDestruida()) {
	    	if (score > game.getHighScore())
	    		game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			game.setScreen(ss);
  			dispose();
	    }
	    batch.end();
	    
	    // Nivel completado
	    if (asteroides.estaVacia() && hirientes.estaVacia()) {
	    	Screen ss = new PantallaJuego(game,ronda+1, nave.getVidas(), score,
	    			velAsteroides + 5, cantAsteroides+10);
			game.setScreen(ss);
			dispose();
	    } 	 
	}
	
	public void agregarPuntaje(int puntaje) {
		this.score += puntaje;
	}
	
	@Override
	public void show() {
		gameMusic.play();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		this.explosionSound.dispose();
		this.gameMusic.dispose();
	}
   
}