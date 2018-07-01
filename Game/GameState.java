package Game;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.IState;
import org.techtown.mypassion.R;

import java.util.ArrayList;
import java.util.Random;

public class GameState implements IState {
    // 멤버변수추가
    private Player m_player;
    private BackGround m_background;
//    private Enemy_1 enem;
    ArrayList<Enemy> m_enemlist= new ArrayList<Enemy>( );
    long LastRegenEnemy= System.currentTimeMillis( ); //최근시간을 도입 적군의 배열에서 사용
    Random randEnem= new Random( );


    @Override
    public void Destroy( ) { }
    @Override
    public void Init( ) {
        m_player= new Player(AppManager.getInstance( ).getBitmap(R.drawable. player));
        m_background= new BackGround(1);
//        enem= new Enemy_1( );
    }
    @Override
    public void Render(Canvas canvas) {
        m_background.Draw(canvas);
        m_player.Draw (canvas);
//        enem.Draw(canvas);
        for( Enemy enem: m_enemlist) {
            enem.Draw(canvas);
        }
    }

    @Override
    public void Update( ) {
        long GameTime= System.currentTimeMillis( );
        m_player.Update(GameTime);
        m_background.Update();
//        enem.Update(GameTime); //마냥 적한명의 위치를 이동시켜
        for( Enemy enem: m_enemlist) {
            enem.Update(GameTime);
        }
        MakeEnemy();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return false;
    }
    @Override
    public boolean onKeyDown( int keyCode, KeyEvent event ) {
        // 키입력에따른플레이어이동
        int x = m_player.getX();
        int y = m_player.getY();
        if(keyCode== KeyEvent. KEYCODE_DPAD_LEFT) // 왼쪽
            m_player.setPosition( x-1, y );
        if(keyCode== KeyEvent. KEYCODE_DPAD_RIGHT) // 오른쪽
            m_player.setPosition( x+1, y );
        if(keyCode== KeyEvent. KEYCODE_DPAD_UP) // 위
            m_player.setPosition( x, y-1 );
        if(keyCode== KeyEvent. KEYCODE_DPAD_DOWN) // 아래
            m_player.setPosition( x, y+1 );
        return true;
    }
    @Override
    public boolean Destroy(MotionEvent event) {
        return false;
    }
    public void MakeEnemy( ) {
        if(System.currentTimeMillis( )-LastRegenEnemy>= 1000 ) {
            LastRegenEnemy= System.currentTimeMillis( );
            Enemy enem= null;
            int enemtype= randEnem.nextInt(3);

            if ( enemtype== 0) enem= new Enemy_1( );
            else if ( enemtype== 1) enem= new Enemy_2( );
            else if ( enemtype== 2)enem= new Enemy_3( );

            enem.setPosition(randEnem.nextInt(600), -60);//x축의 싸이즈 수정하기
            enem.movetype= randEnem.nextInt(3);;
            m_enemlist.add( enem);
        }
    }
}