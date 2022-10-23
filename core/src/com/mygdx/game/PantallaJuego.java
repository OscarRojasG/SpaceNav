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
import com.mygdx.game.consumibles.VidaExtra;


public class PantallaJuego implements Screen {
	private final int ASTEROID_MIN_ANGLE = 20;
	private final int ASTEROID_MAX_ANGLE = 70;
	private final int ASTEROID_MIN_SIZE = 40;
	private final int ASTEROID_MAX_SIZE = 60;

	private SpaceNav game;
	private OrthographicCamera camera;	
	private SpriteBatch batch;
	private Sound explosionSound;
	private Music gameMusic;
	private int score;
	private int ronda;
	private int velAsteroides; 
	private int cantAsteroides;
	
	private Nave nave;
	private ArrayList<Asteroide> balls1 = new ArrayList<>();
	private ArrayList<Asteroide> balls2 = new ArrayList<>();
	private ArrayList<Bala> balas = new ArrayList<>();
	private ArrayList<Consumible> consumibles = new ArrayList<>();

	public PantallaJuego(SpaceNav game, int ronda, int vidas, int score,  
			int velAsteroides, int cantAsteroides) {
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		this.velAsteroides = velAsteroides;
		this.cantAsteroides = cantAsteroides;
		
		batch = game.getBatch();
		camera = new OrthographicCamera();	
		camera.setToOrtho(false, 800, 640);
		//inicializar assets; musica de fondo y efectos de sonido
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.5f);
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav")); //
		
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.5f);
		gameMusic.play();
		
	    // cargar imagen de la nave, 64x64   
	    nave = new Nave(
	    		Gdx.graphics.getWidth()/2-50,
	    		30,
	    		new Texture(Gdx.files.internal("MainShip3.png")),
	    		Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")));
        
        //crear asteroides
        generarAsteroides();
	}
	
	private void generarAsteroides() {	
	    for (int i = 0; i < cantAsteroides; i++) {
			float angle = Util.generateRandomInt(ASTEROID_MIN_ANGLE, ASTEROID_MAX_ANGLE);
			angle = (float)Math.toRadians(angle);
			
			float velXAsteroides = velAsteroides * (float)Math.cos(angle);
			float velYAsteroides = velAsteroides * (float)Math.sin(angle);
			
			int size = Util.generateRandomInt(ASTEROID_MIN_SIZE, ASTEROID_MAX_SIZE);
			
	        Asteroide asteroide = new Asteroide(
	        		Util.generateRandomInt(0, Gdx.graphics.getWidth() - size),
	        		Util.generateRandomInt(0, Gdx.graphics.getHeight() - size),
	        		size,
	        		velXAsteroides,
	        		velYAsteroides,
	  	            new Texture(Gdx.files.internal("aGreyMedium4.png")));
	        
	  	    balls1.add(asteroide);
	  	    balls2.add(asteroide);
	  	}
	}
	
	public void generarPowerUp(float x, float y, float velX, float velY) {
		Consumible consumible = new VidaExtra(x, y, 40, 40, velX, velY, new Texture(Gdx.files.internal("health.png")));
		consumibles.add(consumible);
	}
    
	public void dibujaEncabezado() {
		CharSequence str = "Vidas: " + nave.getVidas() + " Ronda: " + ronda;
		game.getFont().getData().setScale(2f);		
		game.getFont().draw(batch, str, 10, 30);
		game.getFont().draw(batch, "Score:" + this.score, Gdx.graphics.getWidth()-150, 30);
		game.getFont().draw(batch, "HighScore:" + game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
	}
	
	private void crearBala(Nave nave) {
		float x = nave.getX() + nave.getAncho() / 2;
		float y = nave.getY() + nave.getAlto();
		float vel = 125;
		
		Bala bala = new Bala(x, y, vel, new Texture(Gdx.files.internal("rocket2.png")));
		balas.add(bala);
	}
	
	private void destruirAsteroide(Asteroide asteroide) {
		explosionSound.play();
		balls1.remove(asteroide);
		balls2.remove(asteroide);
		score += 10;
		
		// Probabilidad 1/10 de generar un PowerUp aleatorio
		int n = Util.generateRandomInt(0, 9);
		if (n == 0)
			generarPowerUp(asteroide.getX(), asteroide.getY(), asteroide.getVelocidadX(), asteroide.getVelocidadY());
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
		dibujaEncabezado();
		
	    if (!nave.estaHerida()) {
	    	// Colisiones entre balas y asteroides y su destrucción
	    	for (int i = 0; i < balas.size(); i++) {
	    		Bala bala = balas.get(i);
	    		bala.actualizar();
	    		for (int j = 0; j < balls1.size(); j++) {
	    			Asteroide asteroide = balls1.get(j);
	    			if (asteroide.verificarColision(bala)) {
	    				destruirAsteroide(asteroide);
	    				destruirBala(bala);
	    				j--;
		            }   	  
		  	    }
	    	}
	    	
	    	// Colisión entre consumibles y nave
	    	for (int i = 0; i < consumibles.size(); i++) {
	    		Consumible consumible = consumibles.get(i);
	    		((ObjetoEspacial)consumible).actualizar();
	    		if (((ObjetoEspacial)consumible).verificarColision(nave)) {
	    			consumible.agregarEfecto(nave);
	    			destruirConsumible(consumibles.get(i));
	    		}
	    	}
	    	
		    // Actualizar movimiento de asteroides dentro del area
	    	for (Asteroide asteroide : balls1) 
	    		asteroide.actualizar();
	    	
		    // Colisiones entre asteroides y sus rebotes  
	    	for (int i = 0; i < balls1.size(); i++) {
	    		Asteroide ball1 = balls1.get(i);   
		        for (int j = 0; j < balls2.size(); j++) {
		        	Asteroide ball2 = balls2.get(j); 
		        	if (i < j) 
		        		ball1.verificarColision(ball2); 
		        }
		    }
	    	
	    	if (nave.disparar()) {
	    		crearBala(nave);
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
	    for (int i = 0; i < balls1.size(); i++) {
	    	Asteroide asteroide = balls1.get(i);
	    	asteroide.dibujar(batch);
	    	
	    	if (asteroide.verificarColision(nave)) {
	    		nave.herir();
	    		balls1.remove(i);
	    		balls2.remove(i);
	    		i--;
	    	}   	  
	    }
	      
	    if (nave.estaDestruida()) {
	    	if (score > game.getHighScore())
	    		game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			ss.resize(1200, 800);
  			game.setScreen(ss);
  			dispose();
	    }
	    batch.end();
	    
	    // Nivel completado
	    if (balls1.size() == 0) {
	    	Screen ss = new PantallaJuego(game,ronda+1, nave.getVidas(), score,
	    			velAsteroides + 5, cantAsteroides+10);
			ss.resize(1200, 800);
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
