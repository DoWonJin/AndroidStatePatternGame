package Game;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.R;

public class Enemy_3 extends Enemy {

        public Enemy_3( ) {
            super(AppManager.getInstance( ).getBitmap(R.drawable. enemy3));
            this.initSpriteData(3, 6);
            hp= 10;
            speed= 15f;
        }
        @Override
        public void Update( long GameTime) {
            super.Update(GameTime);
        }
}