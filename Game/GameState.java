package Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.GraphicObject;
import org.techtown.mypassion.IState;
import org.techtown.mypassion.R;

import java.util.ArrayList;
import java.util.Random;

public class GameState implements IState {
    // 멤버변수추가
    public Player m_player;
    private BackGround m_background;
    private GraphicObject m_keypad;
    private Enemy_1 enem;
    ArrayList<Enemy> m_enemlist= new ArrayList<Enemy>( );
    ArrayList<Missile_Player> m_pmslist= new ArrayList<Missile_Player>( );
    ArrayList<Item> m_itemlist = new ArrayList<Item>( );
    ArrayList<Missile> m_enemmslist= new ArrayList<Missile>( );


    long LastRegenEnemy= System.currentTimeMillis( ); //최근시간을 도입 적군의 배열에서 사용
    long LastRegenMissile= System.currentTimeMillis( );
    long LastRegenitem= System.currentTimeMillis( );
    Random randEnem= new Random( );
    Random randItem= new Random( );
    Canvas _canvas;

    public GameState(){
        AppManager.getInstance( ). m_gameState= this;
    }
    @Override
    public void Destroy( ) { }
    @Override
    public void Init( ) {
        m_player= new Player(AppManager.getInstance( ).getBitmap(R.drawable. player));
        m_background= new BackGround(1);
        m_keypad= new GraphicObject(AppManager.getInstance( ).getBitmap(R.drawable. keypad));
        m_keypad.setPosition(43,385);
//        enem= new Enemy_1( );
    }
    @Override
    public void Render(Canvas canvas) {
        _canvas = canvas;
//        canvas.drawColor(Color.BLACK);
        m_background.Draw(canvas);
        m_player.Draw (canvas);
//       enem.Draw(canvas);
        for(Missile_Player  pms: m_pmslist)pms.Draw(canvas);
        for( Enemy          enem: m_enemlist)enem.Draw(canvas);
        for(Item            item: m_itemlist)item.Draw(canvas);
        for(Missile         enemms: m_enemmslist) { enemms.Draw(canvas); }
        Paint p = new Paint( );
        p.setTextSize(100);
        p.setColor(Color. BLACK);
        canvas.drawText("남은목숨:"+String.valueOf( m_player.getLife( )), 0, 200, p);
//        m_keypad.Draw(canvas);
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
        MakeEnemy();//적들을 발생시킴
        for( int i=m_enemlist.size( )-1; i>= 0; i--) {
            Enemy enem= m_enemlist.get( i);
            enem.Update(GameTime);
            if(enem. state== Enemy. STATE_OUT) m_enemlist.remove( i);
        }
        MakeItem();
        for( int i=m_itemlist.size( )-1; i>= 0; i--) {
            Item item= m_itemlist.get(i);
            item.Update(GameTime);
            if(item.state== item. STATE_OUT) m_itemlist.remove( i);
        }
        for( int i= m_enemmslist.size( )-1; i>= 0; i--) {
            Missile enemms= m_enemmslist.get(i);
            enemms.Update( );
            if( enemms. state== Missile. STATE_OUT) { m_enemmslist.remove(i); }
        }


        //Player의 총을 발생시킴

        CheckCollision( );

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        int action = event.getAction( );
        int a,b;
        a = m_player.getX();
        b = m_player.getY();
        int _x, _y;
        _x = (int) event.getX( );
        _y = (int) event.getY( );

        if(_x<AppManager.getInstance().getDeviceSize().x/2)m_player.setPosition(a-30,b);
        else m_player.setPosition(a+30,b);

        return true;
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

    public void CheckCollision( ) {
        for( int i= m_pmslist.size( )-1; i>= 0; i--) {
            for( int j = m_enemlist.size( )-1; j >= 0; j--) {
                if(CollisionManager.CheckBoxToBox(
                        m_pmslist.get(i).m_BoundBox,
                        m_enemlist.get(j). m_BoundBox)) {
                    m_pmslist.remove(i);
                    m_enemlist.remove(j);
                    return; // 일단루프에서빠져나옴
                }
            }
        }

        for( int i = m_itemlist.size( )-1; i >= 0; i--) { //플레이어가 아이템 획득
            if(CollisionManager.CheckBoxToBox(
                    m_player.m_BoundBox,
                    m_itemlist.get(i). m_BoundBox)) {
                m_player.GetItem(m_itemlist.get(i));
                Paint p = new Paint( );
                p.setTextSize(100);
                p.setColor(Color. BLACK);
                _canvas.drawText("필살기 쳐먹음"
                        , AppManager.getInstance().getDeviceSize().x-600
                        , AppManager.getInstance().getDeviceSize().y-700
                        , p);
                m_itemlist.remove(i);

                return; // 일단루프에서빠져나옴
            }
        }

        for( int i= m_enemlist.size( )-1; i>= 0; i--) {
            if(CollisionManager.CheckBoxToBox(
                    m_player.m_BoundBox,
                    m_enemlist.get(i). m_BoundBox)) {
                m_enemlist.remove(i);
                m_player.destroyPlayer( );
                if( m_player.getLife( ) <= 0 ) System.exit( 0 );
            }
        }

        for( int i= m_enemmslist.size( )-1; i>= 0; i--) {
            if(CollisionManager.CheckBoxToBox(
                    m_player.m_BoundBox,
                    m_enemmslist.get( i). m_BoundBox)) {
                m_enemmslist.remove( i);
                m_player.destroyPlayer( );
                if( m_player.getLife( ) <= 0 ) System.exit( 0 );
            }
        }


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
            enem.setPosition(randEnem.nextInt(AppManager.getInstance().getDeviceSize().x), -60);//적군의 초기 위치를 결정 &&  <x축의 싸이즈 수정하기>

            m_enemlist.add( enem); // 개성이 부여된 적군을 적군무리리스트에 등록
        }
    }
    public void MakeItem( ) {
        if(System.currentTimeMillis( )-LastRegenitem>= 500 ) { //1초간격으로 생성하는 알고리즘: LastRegenEnemy는 시간을 담는 그릇일 뿐...
            //LastRegenEnemy는 밑에 코드를 실행하지 않는 이상 변하지 않고 위의 System.currentTimeMills는 계속 증가한다.
            LastRegenitem= System.currentTimeMillis( );

            Item item= null; //1개의 itme의 정보를 담을 참조변수(그릇)생성
            int itemtype= randItem.nextInt(3); //종류를 결정
//            if ( itemtype== 0) item= new Item_Speed( );
//            else if ( itemtype== 1) enem= new Item_Hp( );
            item= new Item_Skill( );

            item.movetype= randItem.nextInt(3);//item의 행동패턴을 결정
            item.setPosition(randItem.nextInt(AppManager.getInstance().getDeviceSize().x), -60);//적군의 초기 위치를 결정 &&  <x축의 싸이즈 수정하기>

            m_itemlist.add(item); // 개성이 부여된 적군을 적군무리리스트에 등록
        }
    }
}