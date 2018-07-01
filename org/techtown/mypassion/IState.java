package org.techtown.mypassion;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public interface IState{
        public void Init( );
// 이상태로바뀌었을때실행할것들
        public void Destroy( );
// 다른상태로바뀌었을때실행할것들
        public void Update( );
// 지속적으로수행할것들
        public void Render(Canvas canvas);
// 그려야할것들
        public boolean onKeyDown( int keyCode, KeyEvent evnet);
// 키입력처리
        public boolean onTouchEvent(MotionEvent event);
// 터치입력처리
        public boolean Destroy(MotionEvent event);

}
