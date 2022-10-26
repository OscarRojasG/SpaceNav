package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.asteroides.BigAsteroid;
import com.mygdx.game.asteroides.MediumAsteroid;
import com.mygdx.game.asteroides.SmallAsteroid;
import com.mygdx.game.consumibles.Supernave;
import com.mygdx.game.consumibles.VidaExtra;
import com.mygdx.game.damages.DesechoCohete;
import com.mygdx.game.damages.Satelite;


public class PantallaJuego implements Screen {
	private final int ASTEROID_MIN_ANGLE = 20;
	private final int ASTEROID_MAX_ANGLE = 70;
	private static final int Y_RESOLUTION = Gdx.graphics.getHeight()-10;
	private static final int X_RESOLUTION = Gdx.graphics.getWidth()-10;

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
	private ArrayList<Asteroide> asteroides = new ArrayList<>();
	private ArrayList<Bala> balas = new ArrayList<>();
	private ArrayList<Consumible> consumibles = new ArrayList<>();
	private ArrayList<Hiriente> herir = new ArrayList<>();

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
        
        //crear asteroides
        generarAsteroides();
	}
	
	private void generarAsteroides() {	
	    for (int i = 0; i < cantAsteroides; i++) {
			float angle = Util.generateRandomInt(ASTEROID_MIN_ANGLE, ASTEROID_MAX_ANGLE);
			angle = (float)Math.toRadians(angle);
			
			float velXAsteroides = velAsteroides * (float)Math.cos(angle);
			float velYAsteroides = velAsteroides * (float)Math.sin(angle);
			
			int size = Util.getAsteroid(ronda);
			
			Asteroide asteroide;
			
			if(size == 0)
			{
				asteroide = new SmallAsteroid(velXAsteroides, velYAsteroides);
			}
			else if (size == 1)
			{
				asteroide = new MediumAsteroid(velXAsteroides, velYAsteroides);
			}
			else 
			{
				asteroide = new BigAsteroid(velXAsteroides, velYAsteroides);
			}
	        
	  	    asteroides.add(asteroide);
	  	}
	}
	
	public void generarPowerUp(float x, float y, float velX, float velY) {
		if (consumibles.size() > 2) 
		{
			return;
		}
		int n = Util.generateRandomInt(1, 32);
		Consumible consumible = null;
		
		if((n == 1) || ( 7 <= n && n <= 11) || ( 23< n && n <= 25 )|| n == 32){
			consumible = new VidaExtra(x, y, 
									   velX, velY
									   );
		}
		else {
			consumible = new Supernave(x, y, 
									   velX, velY
									   );
		} 
		
		consumibles.add(consumible);
	}
	
	public void generarPowerLess(float x, float y, float velX, float velY) {
		if (herir.size() > 1)
		{
			return;
		}
		
		int n = Util.generateRandomInt(1, 32);
		Hiriente powerLess = null;
		
		if((n == 1) || ( 7 <= n && n <= 11) || ( 23< n && n <= 25 )|| n == 32){
			powerLess = new Satelite(x, y,
									 velX, velY
									);
		}
		else {
			powerLess = new DesechoCohete(x, y, 
									 	  velX, velY
									 	 );
		} 
		
		herir.add(powerLess);
	}
    
	public void dibujaEncabezado() {
		CharSequence str = "Vidas: " + nave.getVidas() + " Ronda: " + ronda;
		game.getFont().getData().setScale(2f);		
		game.getFont().draw(batch, str, 10, 30);
		game.getFont().draw(batch, "Score:" + this.score, Gdx.graphics.getWidth()-150, 30);
		game.getFont().draw(batch, "HighScore:" + game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
	}
	
	private void destruirAsteroide(Asteroide asteroide) {
		explosionSound.play();
		asteroides.remove(asteroide);
		score += 10;
		
		if(ronda == 1) 
		{
			return;
		}
		
		// Probabilidad 1/10 de generar un PowerUp aleatorio
		int n = Util.generateRandomInt(1, 10 * ronda);
		if (n == 1 || n == 20) {
			generarPowerUp(asteroide.getX(), asteroide.getY(), 
					       asteroide.getVelocidadX(), asteroide.getVelocidadY());
		}
		
		// Probabilidad 1/10 de generar un objeto dañino aleatorio
		if (n == 2 || n == 10) {
			int option = Util.generateRandomInt(0, 1);
			int horizontal;
			int vertical;
			int velX = 200;
			int velY = 200;
			
			if (option == 0) {
				vertical = Util.generateRandomInt(10, Y_RESOLUTION);
				horizontal = Util.generateRandomBetween(0, X_RESOLUTION);
				velY = 0;
				if(horizontal != 0) {
					velX *= -1;
				}
			}
			else {
				horizontal = Util.generateRandomInt(10, X_RESOLUTION);
				vertical= Util.generateRandomBetween(0, Y_RESOLUTION);
				velX = 0;
				if(vertical != 0) {
					velY *= -1;
				}
			}
			
			generarPowerLess(horizontal, vertical,
							 velX , velY
							);
		}
	}
	
	private void destruirBala(Bala bala) {
		balas.remove(bala);
	}
	
	private void destruirConsumible(Consumible consumible) {
		consumibles.remove(consumible);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(font, 0, 0);
		dibujaEncabezado();
		
	    if (!nave.estaHerida()) {
	    	// Colisiones entre balas y asteroides y su destrucción
	    	for (int i = 0; i < balas.size(); i++) {
	    		Bala bala = balas.get(i);
	    		bala.actualizar();
	    		for (int j = 0; j < asteroides.size(); j++) {
	    			Asteroide asteroide = asteroides.get(j);
	    			if (asteroide.verificarColision(bala)) {
	    				destruirAsteroide(asteroide);
	    				destruirBala(bala);
	    				j--;
		            }   	  
		  	    }
	    	}
	    	
	    	// Colisiones entre balas e hirientes y su destruccion
	    	
	    	// Colisión entre consumibles y nave
	    	for (int i = 0; i < consumibles.size(); i++) {
	    		Consumible consumible = consumibles.get(i);
	    		((ObjetoEspacial)consumible).actualizar();
	    		
	    		if (((ObjetoEspacial)consumible).verificarColision(nave)) {
	    			consumible.agregarEfecto(nave);
	    			destruirConsumible(consumible);
	    			continue;
	    		}
	    		
	    		if (consumible.noUsado()) {destruirConsumible(consumible);}
	    	}
	    	
		    // Actualizar movimiento de asteroides dentro del area
	    	for (Asteroide asteroide : asteroides) 
	    		asteroide.actualizar();
	    	
		    // Colisiones entre asteroides y sus rebotes  
	    	for (int i = 0; i < asteroides.size(); i++) {
	    		Asteroide ball1 = asteroides.get(i);   
		        for (int j = 0; j < asteroides.size(); j++) {
		        	Asteroide ball2 = asteroides.get(j); 
		        	if (i < j) 
		        		ball1.verificarColision(ball2); 
		        }
		    }
	    	
	    	if (nave.disparar()) {
	    		Bala bala = nave.generarBala();
	    		balas.add(bala);
	    	}
	    }
	    
	    // Dibujar balas
	    for (Bala b : balas) {       
	    	b.dibujar(batch);
	    }
	     
	    // Dibujar balas
	    for (Consumible c : consumibles) {       
	    	((ObjetoEspacial)(c)).dibujar(batch);
	    }
	    
	    nave.actualizar();
	    nave.dibujar(batch);
	    
	    // Dibujar asteroides y manejar colision con nave
	    for (int i = 0; i < asteroides.size(); i++) {
	    	Asteroide asteroide = asteroides.get(i);
	    	asteroide.dibujar(batch);
	    	
	    	if (asteroide.verificarColision(nave)) {
	    		nave.herir();
	    		asteroides.remove(i);
	    		i--;
	    	}   	  
	    }
	      
	    if (nave.estaDestruida()) {
	    	if (score > game.getHighScore())
	    		game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			game.setScreen(ss);
  			dispose();
	    }
	    batch.end();
	    
	    // Nivel completado
	    if (asteroides.size() == 0) {
	    	Screen ss = new PantallaJuego(game,ronda+1, nave.getVidas(), score,
	    			velAsteroides + 5, cantAsteroides+5);
			game.setScreen(ss);
			dispose();
	    } 	 
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
