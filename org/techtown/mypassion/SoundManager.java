package org.techtown.mypassion;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

public class SoundManager{
        public static final int SOUND_EFFECT_1= 1;
        public static final int SOUND_EFFECT_2= 2;
        public static final int SOUND_EFFECT_3= 3;

        private SoundPool m_SoundPool;
        private HashMap m_SoundPoolHashMap; //불러온사운드의아이디값을지정할해시맵
        private AudioManager m_AudioManager; //사운드관리를위한오디오매니저
        private Context m_Activity;

        public void Init(Context _context) {
            // 멤버변수생성과초기화
            m_SoundPool= new SoundPool(4, AudioManager. STREAM_MUSIC, 0);
            m_SoundPoolHashMap= new HashMap( );
            m_AudioManager= (AudioManager)_context.getSystemService(Context. AUDIO_SERVICE);
            m_Activity= _context;
        }
        public void addSound( int _index, int _soundID) {
            int id = m_SoundPool.load( m_Activity, _soundID, 1); // 사운드를로드
            m_SoundPoolHashMap.put(_index, id); // 해시맵에아이디값을받아온인덱스저장
        }

        public void play(int _index) {
    // 사운드재생
            float streamVolume= m_AudioManager.getStreamVolume
                    (AudioManager. STREAM_MUSIC);
            streamVolume= streamVolume/ m_AudioManager.getStreamMaxVolume
                    (AudioManager. STREAM_MUSIC);
            m_SoundPool.play((Integer) m_SoundPoolHashMap.get(_index),
                    streamVolume, streamVolume, 1, 0, 1f);
        }

        public void playLooped( int _index) {
    // 사운드반복재생
            float streamVolume= m_AudioManager.getStreamVolume
                    (AudioManager. STREAM_MUSIC);
            streamVolume= streamVolume/ m_AudioManager.getStreamMaxVolume
                    (AudioManager. STREAM_MUSIC);
            m_SoundPool.play((Integer) m_SoundPoolHashMap.get(_index),
                    streamVolume, streamVolume, 1, -1, 1f);
        }

        private static SoundManager s_instance;
        public static SoundManager getInstance( ) {
            if(s_instance== null) s_instance= new SoundManager( );
            return s_instance;
            }
}