package org.techtown.mypassion;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import Game.GameState;

public class AppManager{

        private GameView m_gameView;
        private Resources m_resources;
        private Context m_context;
        private Canvas m_canvas;
        void setGameView(GameView _gameView) {
            m_gameView= _gameView;
        }
        void setResources(Resources _resources) {
            m_resources= _resources;
        }
        void setContext(Context _context){ m_context = _context;}
        void setCanvas(Canvas _canvas){m_canvas = _canvas;}

        public  GameView getGameView( ) {
            return m_gameView;
        }
        public  Resources getResource( ) {
            return m_resources;
        }
        public  Context getContext(){return m_context;}
        public Canvas getCanvas(){return m_canvas;}
        public Bitmap getBitmap(int r) {
            return BitmapFactory.decodeResource( m_resources, r);
        }
        public GameState m_gameState;
        private static AppManager s_instance;

        public static AppManager getInstance( ) {
            if(s_instance== null) s_instance= new AppManager( );
            return s_instance;
        }
        public Point getDeviceSize(){
            Point point = new Point();
            int w;
            int h;
            Display display;
            display = ((WindowManager)m_context.getSystemService(m_context.WINDOW_SERVICE)).getDefaultDisplay();
            w = display.getWidth();
            h = display.getHeight();
            point.x = w;
            point.y = h;
            return point;
        }
        public Point getPictureSize(int r){
            Point point = new Point();
            int w;
            int h;
            w = getBitmap(r).getWidth();
            h = getBitmap(r).getHeight();
            point.x = w;
            point.y = h;
            return point;
        }



}
