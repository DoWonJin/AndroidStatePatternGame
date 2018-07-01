package Game;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.R;

public class Enemy_2 extends Enemy {

        public Enemy_2( ) {
            super(AppManager.getInstance( ).getBitmap(R.drawable. enemy2));
            this.initSpriteData(3, 6);
            hp= 10;
            speed= 10f;
        }
        @Override
        public void Update( long GameTime) {
            super.Update(GameTime);

        }
}