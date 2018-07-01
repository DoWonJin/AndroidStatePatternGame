package Game;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.R;

public class Item_Skill extends Item {
    int cnt = 1;


    public Item_Skill(){
        super(AppManager.getInstance().getBitmap(R.drawable.item1));
        this.initSpriteData(m_bitmap.getWidth()/4,m_bitmap.getHeight(),2,4);
        speed= 15f;
    }
    @Override
    public void Update(long GameTime){
        super.Update(GameTime);

    }
}
