package ui;

import static utilz.Constants.UI.PauseButtons.SOUND_SIZE;
import static utilz.Constants.UI.VolumeButtons.SLIDER_WIDTH;
import static utilz.Constants.UI.VolumeButtons.VOLUME_HEIGHT;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import main.Game;

public class AudioOptions {

	private VolumeButton volumeButton;
	private SoundButton musicButton, sfxButton;

	private Game game;

	public AudioOptions(Game game) {
		this.game = game;

		createSoundButtons();
		createVolumeButton();
	}

	private void createSoundButtons() {
		int soundX = (int) (450 * Game.SCALE);
		int musicY = (int) (140 * Game.SCALE);
		int sfxY = (int) (186 * Game.SCALE);

		musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
	}

	private void createVolumeButton() {
		int vX = (int) (309 * Game.SCALE);
		int vY = (int) (278 * Game.SCALE);
		volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
	}

	public void update() {
		musicButton.update();
		sfxButton.update();

		volumeButton.update();
	}

	public void draw(Graphics g) {
		// Sound buttons
		musicButton.draw(g);
		sfxButton.draw(g);

		// Volume slider
		volumeButton.draw(g);
	}

	public void mouseDragged(MouseEvent e) {
		if (volumeButton.isMousePressed()) {
			float valueBefore = volumeButton.getFloatValue();

			volumeButton.changeX(e.getX());

			float valueAfter = volumeButton.getFloatValue();

			if (valueBefore != valueAfter) {
				game.getAudioPlayer().setVolume(valueAfter);
			}
		}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if (IsIn(e, musicButton)) {
			musicButton.setMousePressed(true);
		} else if (IsIn(e, sfxButton)) {
			sfxButton.setMousePressed(true);
		} else if (IsIn(e, volumeButton)) {
			volumeButton.setMousePressed(true);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (IsIn(e, musicButton)) {
			if (musicButton.isMousePressed()) {
				musicButton.setMuted(!musicButton.isMuted());
				game.getAudioPlayer().toggleSongMute();
			}
		} else if (IsIn(e, sfxButton)) {
			if (sfxButton.isMousePressed()) {
				sfxButton.setMuted(!sfxButton.isMuted());
				game.getAudioPlayer().toggleEffectMute();
			}
		}

		musicButton.resetBools();
		sfxButton.resetBools();
		volumeButton.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		volumeButton.setMouseOver(false);

		if (IsIn(e, musicButton)) {
			musicButton.setMouseOver(true);
		} else if (IsIn(e, sfxButton)) {
			sfxButton.setMouseOver(true);
		} else if (IsIn(e, volumeButton)) {
			volumeButton.setMouseOver(true);
		}
	}

	public boolean IsIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}
}
