package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class MainController extends ApplicationAdapter implements InputProcessor{
	SpriteBatch batch;

	World world;
	Box2DDebugRenderer debugRenderer;

	Camera camera;

	private float SCALE = 32f;

	@Override
	public void create () {
		Box2D.init();
		batch = new SpriteBatch();

		camera = new OrthographicCamera(800, 600);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		world = new World(new Vector2(0, -98), true);
		debugRenderer = new Box2DDebugRenderer();

		createGround();
		createBike();

		world.setContactListener(new ContentListenerClass());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.end();

		debugRenderer.render(world, camera.combined);

		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
	}

	public void createGround() {
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(0, 11));

		Body groundBody = world.createBody(groundBodyDef);

		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(camera.viewportWidth-1, 10.0f);
		groundBody.createFixture(groundBox, 0.0f);
		groundBox.dispose();
	}

	public void createBike() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(100, 300);

		Body body = world.createBody(bodyDef);
		CircleShape circle = new CircleShape();
		circle.setRadius(32f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = .5f;
		fixtureDef.friction = .0f;
		fixtureDef.restitution = 1f;
		Fixture fixture = body.createFixture(fixtureDef);
		circle.dispose();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		world.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		System.out.println("mouse clicked");
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		System.out.println("mouse move");
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
