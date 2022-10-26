package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaMenu implements Screen{
	
	private SpaceNav game;
	private OrthographicCamera camera;
	private Texture screen;
	private SpriteBatch batch;

	public PantallaMenu(SpaceNav game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		Util.setOtrhoCam(camera);
		screen = new Texture(Gdx.files.internal("Fondo.png"));
		batch = game.getBatch();
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		batch.draw(screen, 0, 0);
		game.getFont().draw(batch, "Bienvenido a Space Navigation !", 140, 400);
		game.getFont().draw(batch, "Presione cualquier tecla o haga click para comenzar ...", 100, 300);
        batch.end();

		if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
			Screen ss = new PantallaJuego(game, 1, 0);
			game.setScreen(ss);
			dispose();
		}
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}