package com.mygdx.game;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.colecciones.ColeccionAsteroides;
import com.mygdx.game.colecciones.ColeccionBalas;
import com.mygdx.game.colecciones.ColeccionConsumibles;
import com.mygdx.game.colecciones.ColeccionHirientes;

public class PantallaJuego implements Screen {
	private static final Music musica = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav"));
	
	private SpaceNav game;
	private BitmapFont font;

	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;

	private int ronda;
	private int puntaje;
	
	private Nave nave;
	private ColeccionAsteroides asteroides;
	private ColeccionBalas balas;
	private ColeccionConsumibles consumibles;
	private ColeccionHirientes hirientes;

	public PantallaJuego(SpaceNav game) {
		this(game, 1, 0);
	}

	public PantallaJuego(SpaceNav game, int ronda, int puntaje) {
		this.game = game;
		this.ronda = ronda;
		this.puntaje = puntaje;
		
		this.font = game.getFont();
		this.batch = game.getBatch();
		this.shapeRenderer = game.getShapeRenderer();
		this.game = game;
		this.font = game.getFont();
		this.batch = game.getBatch();
		
		musica.setLooping(true);
		musica.setVolume(0.5f); // Debería ser parte del archivo
		musica.play();
		
		int navePosX = Gdx.graphics.getWidth()/2 - 50;
		int navePosY = 30;
		
	    nave = new Nave(navePosX, navePosY);
               
        asteroides = new ColeccionAsteroides();
        consumibles = new ColeccionConsumibles();
        hirientes = new ColeccionHirientes();
        balas = new ColeccionBalas();
        
        // Iniciar ronda

		int cantAsteroides = 10 + (ronda - 1) * 2;
		int velAsteroides = 120 + (ronda - 1) * 20;

		asteroides.crear(cantAsteroides, velAsteroides, ronda);

	}
	
	public void dibujarEncabezado() {
		CharSequence str = "Vidas: " + nave.getVidas() + " Ronda: " + ronda;
		font.getData().setScale(2f);	
		font.draw(batch, str, 10, 30);
		font.draw(batch, "Score:" + puntaje, Gdx.graphics.getWidth()-150, 30);
		font.draw(batch, "HighScore:" + game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		dibujarEncabezado();
		
		if (nave.estaDestruida()) {
			finalizarJuego();
		}
		
		if (asteroides.estaVacia() && hirientes.estaVacia()) {
			avanzarRonda();
		}
		
	    if (!nave.estaHerida()) {
	    	Iterator<Enemigo> iteratorAsteroides = asteroides.getAsteroides();
	    	while(iteratorAsteroides.hasNext()) {
	    		Enemigo a = iteratorAsteroides.next();
	    		if(balas.verificarColisiones(a)) {
	    			iteratorAsteroides.remove();
	    			asteroides.eliminar(a);
	    			a.explotar();
	    			consumibles.generar(a.getX(), a.getY(), a.getVelocidadX(), a.getVelocidadY());
	    			agregarPuntaje(10);
	    		}
	    	}
	    	
	    	Iterator<Enemigo> iteratorHirientes = hirientes.getHirientes();
	    	while(iteratorHirientes.hasNext()) {
	    		Enemigo h = iteratorHirientes.next();
	    		if(balas.verificarColisiones(h)) {
	    			iteratorHirientes.remove();
	    			int sumScore = ((Hiriente)h).getScoreChange();
	    			hirientes.eliminar(h, true);
	    			agregarPuntaje(sumScore);
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
	    	
		    asteroides.actualizar();
		    hirientes.actualizar();
		    consumibles.actualizar();
		    balas.actualizar();
		    
		    asteroides.verificarColisiones(nave);
		    hirientes.verificarColisiones(nave);
	    }
	    
	    nave.actualizar();
	    
	    asteroides.dibujar(batch);
	    hirientes.dibujar(batch);
	    consumibles.dibujar(batch);
	    balas.dibujar(batch);
	    batch.end();
	    
	    nave.dibujar(shapeRenderer);
	}
	
	public void finalizarJuego() {
		game.setScreen(new PantallaGameOver(game));		
		if (puntaje > game.getHighScore()) {
			game.setHighScore(puntaje);
		}
		
		musica.stop();
	}
	
	public void avanzarRonda() {
		Screen screen = new PantallaJuego(game, ronda + 1, puntaje);
		game.setScreen(screen);
		dispose();
	}
	
	public void agregarPuntaje(int puntaje) {
		this.puntaje += puntaje;
	}
	
	@Override
	public void show() {
		musica.play();
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
		
	}
   
}