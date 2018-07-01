package Game;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.R;

public class Missile_Enemy extends Missile {
        public Missile_Enemy( int x, int y) {
            super(AppManager.getInstance( ).getBitmap(R.drawable. missile_2));
            this.setPosition(x, y);
        }
        @Override
        public void Update( ) {
            //미사일이아래로발사되는효과
            super.Update();
            m_y+= 5;
            if(m_y> AppManager.getInstance().getDeviceSize().y) state= STATE_OUT;
        }
}