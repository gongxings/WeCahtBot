package com.danggui.wechat.ui.tree;

import javax.swing.JTree;

/**
 * A tree node user object, for use with a {@link JTree}, that tracks whether it
 * is checked.
 * <p>
 * Thanks to John Zukowski for the <a
 * href="http://www.java2s.com/Code/Java/Swing-JFC/CheckBoxNodeTreeSample.htm"
 * >sample code</a> upon which this is based.
 * </p>
 *
 */
public class CheckBoxNodeData {

	private String text;
    private boolean checked;

	public CheckBoxNodeData(final String text, final boolean checked) {
		this.text = text;
		this.checked = checked;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(final boolean checked) {
		this.checked = checked;
	}

	public String getText() {
		return text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[" + text + "/" + checked + "]";
	}

}