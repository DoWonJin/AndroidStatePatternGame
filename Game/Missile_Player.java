package Game;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.R;

public class Missile_Player extends Missile {
        public Missile_Player(int x, int y) {
            super(AppManager.getInstance( ).getBitmap(R.drawable.missile_1));
            this.setPosition(x, y);
        }
        @Override
        public void Update( ) {
            m_y-= 30;
            m_BoundBox.left= m_x;
            m_BoundBox.top= m_y;
            m_BoundBox.right= m_x+m_msl.getWidth();
            m_BoundBox.bottom= m_y+ m_msl.getHeight();

            if(m_y< 0) state= STATE_OUT;

        }
}