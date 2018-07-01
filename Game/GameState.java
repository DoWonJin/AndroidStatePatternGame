package Game;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.GraphicObject;
import org.techtown.mypassion.IState;
import org.techtown.mypassion.R;

import java.util.ArrayList;
import java.util.Random;

public class GameState implements IState {
    // 멤버변수추가
    private Player m_player;
    private BackGround m_background;
    private GraphicObject m_keypad;
//    private Enemy_1 enem;
    ArrayList<Enemy> m_enemlist= new ArrayList<Enemy>( );
    ArrayList<Missile_Player> m_pmslist= new ArrayList<Missile_Player>( );

    long LastRegenEnemy= System.currentTimeMillis( ); //최근시간을 도입 적군의 배열에서 사용
    long LastRegenMissile= System.currentTimeMillis( );
    Random randEnem= new Random( );


    @Override
    public void Destroy( ) { }
    @Override
    public void Init( ) {
        m_player= new Player(AppManager.getInstance( ).getBitmap(R.drawable. player));
        m_background= new BackGround(1);
        m_keypad= new GraphicObject(AppManager.getInstance( ).getBitmap(R.drawable. keypad));
        m_keypad.setPosition(0,800);
//        enem= new Enemy_1( );
    }
    @Override
    public void Render(Canvas canvas) {
        m_background.Draw(canvas);
        m_player.Draw (canvas);
//        enem.Draw(canvas);
        for(Missile_Player  pms: m_pmslist)pms.Draw(canvas);
        for( Enemy          enem: m_enemlist)enem.Draw(canvas);

        m_keypad.Draw(canvas);
    }

    @Override
    public void Update( ) {
        long GameTime= System.currentTimeMillis( );
        m_player.Update(GameTime);
        m_background.Update();
//        enem.Update(GameTime); //마냥 적한명의 위치를 이동시켜
        for( int i=m_pmslist.size( )-1; i>= 0; i--) {
            Missile_Player pms= m_pmslist.get( i);
            pms.Update( );
            if( pms. state== Missile. STATE_OUT) m_pmslist.remove( i);
        }
        for( int i=m_enemlist.size( )-1; i>= 0; i--) {
            Enemy enem= m_enemlist.get( i);
            enem.Update(GameTime);
            if(enem. state== Enemy. STATE_OUT) m_enemlist.remove( i);
        }
        MakeEnemy();
        if(System.currentTimeMillis( )-LastRegenMissile>= 500 ) { //1초간격으로 발사하는 알고리즘: LastRegenMissile는 시간을 담는 그릇일 뿐...
            LastRegenMissile = System.currentTimeMillis();
            m_pmslist.add(new Missile_Player(m_player.getX(), m_player.getY()));         //플레이어의 현위치에서 미사일 생성및 리스트에 등록
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return false;
    }
    @Override
    public boolean onKeyDown( int keyCode, KeyEvent event ) {
        // 키입력에따른플레이어이동
        int x = m_player.getX();
        int y = m_player.getY();                             //플레이어 현위치
        if(keyCode== KeyEvent. KEYCODE_DPAD_LEFT) // 왼쪽
            m_player.setPosition( x-1, y );
        if(keyCode== KeyEvent. KEYCODE_DPAD_RIGHT) // 오른쪽
            m_player.setPosition( x+1, y );
        if(keyCode== KeyEvent. KEYCODE_DPAD_UP) // 위
            m_player.setPosition( x, y-1 );
        if(keyCode== KeyEvent. KEYCODE_DPAD_DOWN) // 아래
            m_player.setPosition( x, y+1 );
        if(keyCode== KeyEvent. KEYCODE_SPACE) // 스페이스바
            m_pmslist.add (new Missile_Player(x,y));         //플레이어의 현위치에서 미사일 생성및 리스트에 등록
        return true;
    }
    @Override
    public boolean Destroy(MotionEvent event) {
        return false;
    }
    public void MakeEnemy( ) {
        if(System.currentTimeMillis( )-LastRegenEnemy>= 1000 ) { //1초간격으로 발사하는 알고리즘: LastRegenEnemy는 시간을 담는 그릇일 뿐...
            //LastRegenEnemy는 밑에 코드를 실행하지 않는 이상 변하지 않고 위의 System.currentTimeMills는 계속 증가한다.
            LastRegenEnemy= System.currentTimeMillis( );

            Enemy enem= null; //1개의 적군의 정보를 담을 참조변수(그릇)생성
            int enemtype= randEnem.nextInt(3); //적군의 종류를 결정
            if ( enemtype== 0) enem= new Enemy_1( );
            else if ( enemtype== 1) enem= new Enemy_2( );
            else if ( enemtype== 2)enem= new Enemy_3( );

            enem.movetype= randEnem.nextInt(3);//적군의 행동패턴을 결정
            enem.setPosition(randEnem.nextInt(600), -60);//적군의 초기 위치를 결정 &&  <x축의 싸이즈 수정하기>

            m_enemlist.add( enem); // 개성이 부여된 적군을 적군무리리스트에 등록
        }
    }
}