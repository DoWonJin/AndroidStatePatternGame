package Game;

import android.graphics.Bitmap;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.R;

public class Enemy_1 extends Enemy {

        public Enemy_1( ) {
            super(AppManager.getInstance( ).getBitmap(R.drawable. enemy1));
            this.initSpriteData(m_bitmap.getWidth()/6,m_bitmap.getHeight(),4,6);
            hp= 10;
            speed= 10f;
        }
        @Override
        public void Update( long GameTime) {
            super.Update(GameTime);

        }
}