package Game;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.R;

public class Missile_Player extends Missile {
        public Missile_Player(int x, int y) {
            super(AppManager.getInstance( ).getBitmap(R.drawable.missile_1));
            this.setPosition(x, y);
        }
        public void Update( ) {
            m_y-= 20;
        }
}