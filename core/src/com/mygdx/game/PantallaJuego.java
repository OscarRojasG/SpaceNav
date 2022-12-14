package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.colecciones.ColeccionAsteroides;
import com.mygdx.game.colecciones.ColeccionBalas;
import com.mygdx.game.colecciones.ColeccionConsumibles;
import com.mygdx.game.nave.Nave;
import com.mygdx.game.colecciones.ColeccionBasuraEspacial;

public class PantallaJuego implements Screen {
//	private static final Music musica = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav"));
	
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
	private ColeccionBasuraEspacial basura;
	private BordePantalla bordePantalla;

	private b2Modelo modelo;
    private boolean debugEnabled = false;

	public PantallaJuego(SpaceNav game) {
		this(game, 1, 0); // iniciar por defecto en la primera ronda y sin puntaje
	}

	public PantallaJuego(SpaceNav game, int ronda, int puntaje) {
		this.game = game;
		this.ronda = ronda;
		this.puntaje = puntaje;

		this.shapeRenderer = game.getShapeRenderer();
		this.batch = game.getBatch();

		this.modelo =  b2Modelo.getModelo();
		bordePantalla = new BordePantalla();
		
		this.font = game.getFont();
		
//		musica.setLooping(true);
//		musica.setVolume(0.5f); // Debería ser parte del archivo
//		musica.play();
		
		int navePosX = 0;
		int navePosY = 0;
		
	    nave = new Nave(navePosX, navePosY);
               
        consumibles = new ColeccionConsumibles();
        balas = new ColeccionBalas();
        asteroides = new ColeccionAsteroides();
        basura = new ColeccionBasuraEspacial();
        
        // Iniciar ronda

		int cantAsteroides = 10 + (ronda - 1) * 2;
		int velAsteroides = 140 + (ronda - 1) * 20;

		asteroides.crear(cantAsteroides, velAsteroides, ronda);
	}

    @Override
	public void render(float delta) {
		nave.actualizar();
		modelo.actualizar();
	    
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (debugEnabled) {
            modelo.render();
        }

        asteroides.dibujar(shapeRenderer);
        balas.dibujar(shapeRenderer);
	    nave.dibujar(shapeRenderer);
	    basura.dibujar(shapeRenderer);
	    
	    if (basura.isEmpty()){
	    	basura.generar(400 + (ronda - 1) * 20);
	    }
	    basura.dibujar(shapeRenderer);

    	Bala bala = nave.disparar();
    	if(bala != null)
    		balas.agregar(bala);

        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB))
            debugEnabled = !debugEnabled;
        
        puntaje += asteroides.eliminarDestruidos(consumibles);
        balas.eliminarDestruidos();
        consumibles.eliminarDestruidos();
        basura.eliminarDestruidos();
        
		batch.begin();
		dibujarEncabezado();
		consumibles.dibujar(batch);
	    batch.end();
	    
		if (asteroides.isEmpty() && basura.isEmpty()) {
			avanzarRonda();
		}
		
		if (nave.estaDestruida()) {
			finalizarJuego();
		}
	}
	
	@Override
	public void show() {
//		musica.play();
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
		bordePantalla.eliminar();
		b2Modelo.getModelo().eliminarCuerpo(nave);
		b2Modelo.getModelo().vaciar();
	}
	
	/** Dibuja en la parte inferior de la pantalla los datos del jugador. */
	public void dibujarEncabezado() {
		CharSequence str = "Vidas: " + nave.getVidas() + " Ronda: " + ronda;
		font.getData().setScale(2f);	
		font.draw(batch, str, 10, 30);
		font.draw(batch, "Score:" + puntaje, Gdx.graphics.getWidth()-150, 30);
		font.draw(batch, "HighScore:" + game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
	}
	
	/** Se encarga de que PantallaJuego cambie a PantallaGameOver*/
	public void finalizarJuego() {
		dispose();
		game.setScreen(new PantallaGameOver(game));		
		if (puntaje > game.getHighScore()) {
			game.setHighScore(puntaje);
		}
//		musica.stop();
	}
	
	/** Se encarga de iniciar la PantallaJuego en la siguiente ronda */
	public void avanzarRonda() {
		dispose();
		Screen screen = new PantallaJuego(game, ronda + 1, puntaje);
		game.setScreen(screen);
	}
	
	/** Aumenta el puntaje guardado en la PantallaJuego */
	public void agregarPuntaje(int puntaje) {
		this.puntaje += puntaje;
	}
}
