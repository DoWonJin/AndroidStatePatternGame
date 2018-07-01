package org.techtown.mypassion;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AppManager{

        private GameView m_gameView;
        private Resources m_resources;
        private Context m_context;
        void setGameView(GameView _gameView) {
            m_gameView= _gameView;
        }
        void setResources(Resources _resources) {
            m_resources= _resources;
        }
        void setContext(Context _context){ m_context = _context;}


        public  GameView getGameView( ) {
            return m_gameView;
        }
        public  Resources getResource( ) {
            return m_resources;
        }
        public  Context getContext(){return m_context;}

        public Bitmap getBitmap(int r) {
            return BitmapFactory.decodeResource( m_resources, r);
        }

        private static AppManager s_instance;

        public static AppManager getInstance( ) {
            if(s_instance== null) s_instance= new AppManager( );
            return s_instance;
        }


}
