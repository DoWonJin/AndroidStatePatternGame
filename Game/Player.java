package Game;

import android.graphics.Bitmap;

import org.techtown.mypassion.SpriteAnimation;

public class Player extends SpriteAnimation {
        public Player ( Bitmap bitmap) {
            super(bitmap);
            this.initSpriteData( 3, 6);
            this.setPosition(600, 400);
        }
}
