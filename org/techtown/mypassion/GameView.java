package org.techtown.mypassion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import Game.GameState;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private IState m_state;

//    private SpriteAnimation m_spr; //P.62연습용 player spriteimage

    private GameViewThread m_thread;  //Thread 객체를 View 안에다 만든다. 여러 함수에서 사용할 것이다.

    public GameView(Context context) {
        super(context);
        // 키입력처리를받기위해서
        setFocusable( true);

        AppManager.getInstance( ).setGameView( this);
        AppManager.getInstance( ).setResources(getResources( ));
        AppManager.getInstance().setContext(getContext());//getContext일지 궁금하네
        SoundManager.getInstance( ).Init(context);
        SoundManager.getInstance( ).addSound(1,R.raw.background);
        SoundManager.getInstance( ).addSound(1,R.raw.effect1);
        SoundManager.getInstance( ).addSound(1,R.raw.effect2);
//        SoundManager.getInstance( ).play(1);
//        SoundManager.getInstance( ).playLooped(2);
//        SoundManager.getInstance( ).play(3);
        ChangeGameState( new GameState( ));

//        m_spr= new SpriteAnimation(BitmapFactory.decodeResource(getResources( ), R.drawable.player));
//        m_spr.initSpriteData(3, 6);


        getHolder( ).addCallback( this);
        m_thread= new GameViewThread(getHolder( ), this);




    }

    public void Update( ) {
//        long gameTime= System.currentTimeMillis( );
//        m_spr.Update(gameTime);
        m_state.Update( );


    }
    @Override
    public void onDraw(Canvas canvas) {
//        canvas.drawColor(Color. BLACK);
//        m_spr.Draw(canvas);
          m_state.Render(canvas);
    }

    public void ChangeGameState(IState _state) {
        if (m_state != null)
            m_state.Destroy();
        _state.Init();
        m_state = _state;
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        // 스레드를실행상태로만든다.
        m_thread.setRunning( true);
        // 스레드실행
        m_thread.start( );
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        m_thread.setRunning( false);
        while(retry) {
            try{
                // 스레드를중지시킨다.
                m_thread.join( );
                retry = false;
            } catch(InterruptedException e) {
                // 스레드가종료되도록계속시도
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 키입력에따른플레이어이동
        m_state.onKeyDown(keyCode, event);
        return true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        m_state.onTouchEvent(event);
        return true;
    }
}
