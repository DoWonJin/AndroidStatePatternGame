package Game;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.R;

public class Missile_Player extends Missile {
        public Missile_Player(int x, int y) {
            super(AppManager.getInstance( ).getBitmap(R.drawable.missile_1));
            this.setPosition(x, y);
        }
        public void Update( ) {
            m_y-= 30;
            m_BoundBox.left= m_x;
            m_BoundBox.top= m_y;
            m_BoundBox.right= m_x+AppManager.getInstance().getPictureSize(R.drawable.missile_1).x;
            m_BoundBox.bottom= m_y+ AppManager.getInstance().getPictureSize(R.drawable.missile_1).y;

            if(m_y< 0) state= STATE_OUT;

        }
}