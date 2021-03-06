package game;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {

	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();	
	
	public static void load() {
		try {
			//musicMap.put("Husk", new Music("res/Husk.ogg"));
			//musicMap.put("Mystic", new Music("res/Mystic Beat.ogg"));
			//musicMap.put("Fat", new Music("res/Fat Beat.ogg"));
			musicMap.put("Dying", new Music("/res/Dying.ogg"));
			soundMap.put("Pistol", new Sound("/res/PistolSound.wav"));
			soundMap.put("Rifle", new Sound("/res/RifleSound.wav"));
			soundMap.put("Shotgun", new Sound("/res/ShotgunSound.wav"));
			soundMap.put("Sniper", new Sound("/res/SniperSound.wav"));
			soundMap.put("ReloadTitan", new Sound("/res/ReloadTitanSound.ogg"));
			soundMap.put("ReloadPX4", new Sound("/res/ReloadPX4Sound.wav"));
			soundMap.put("ReloadAR15", new Sound("/res/ReloadAR15Sound.ogg"));
			soundMap.put("ReloadOverUnder", new Sound("/res/ReloadOverUnderSound.ogg"));
			soundMap.put("ReloadM77", new Sound("/res/ReloadM77Sound.ogg"));
			soundMap.put("CycleM77", new Sound("/res/CycleM77Sound.ogg"));
			soundMap.put("BlipMinor", new Sound("/res/BlipMinor.wav"));
			soundMap.put("BlipMajor", new Sound("/res/BlipMajor.wav"));
		} catch (SlickException e) {
			e.getMessage();
		}
	}
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}

}
