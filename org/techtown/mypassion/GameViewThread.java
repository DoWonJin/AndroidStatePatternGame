package org.techtown.mypassion;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameViewThread extends Thread {
// 접근을위한멤버변수
        private SurfaceHolder m_surfaceHolder;
        private GameView m_gameView;
// 스레드실행상태멤버변수
        private boolean m_run= false;
        public GameViewThread(SurfaceHolder surfaceHolder, GameView gameView) {
            m_surfaceHolder= surfaceHolder;
            m_gameView= gameView;
        }
        public void setRunning( boolean run ) {
            m_run= run;
        }
        @Override
        public void run( ) {
            Canvas _canvas;
            while( m_run) {
                _canvas = null;
                try{
                    m_gameView.Update( );
                    // SurfaceHolder를통해Surface에접근해서가져옴
                    _canvas = m_surfaceHolder.lockCanvas( null);
                    synchronized( m_surfaceHolder) {
                        m_gameView.onDraw(_canvas); // 그림을그림
                    }
                } finally{
                    if(_canvas != null)
                    // Surface를화면에표시함
                        m_surfaceHolder.unlockCanvasAndPost(_canvas);
                }
            }
        }
}