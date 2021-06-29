package hisglobal.masterxml.masterworkshop;

import hisglobal.masterxml.masterworkshop.tools.CheckBox;
import hisglobal.masterxml.masterworkshop.tools.CheckBoxDtl;
import hisglobal.masterxml.masterworkshop.tools.Combo;
import hisglobal.masterxml.masterworkshop.tools.ComboDtl;
import hisglobal.masterxml.masterworkshop.tools.Control;
import hisglobal.masterxml.masterworkshop.tools.RadioButton;
import hisglobal.masterxml.masterworkshop.tools.RadioDtl;
import hisglobal.masterxml.masterworkshop.tools.TextArea;
import hisglobal.masterxml.masterworkshop.tools.TextAreaDtl;
import hisglobal.masterxml.masterworkshop.tools.TextBox;
import hisglobal.masterxml.masterworkshop.tools.TextDtl;

public interface MasterConfig
{
	public static final Class CLASS_COMBO = Combo.class;
	public static final Class CLASS_TEXT = TextBox.class;
	public static final Class CLASS_TEXTAREA = TextArea.class;
	public static final Class CLASS_RADIOBUTTON = RadioButton.class;
	public static final Class CLASS_CHECKBOX = CheckBox.class;
	public static final Class CLASS_CONTROL = Control.class;

	public static final Class CLASS_COMBO_DTL = ComboDtl.class;
	public static final Class CLASS_TEXT_DTL = TextDtl.class;
	public static final Class CLASS_TEXTAREA_DTL = TextAreaDtl.class;
	public static final Class CLASS_RADIOBUTTON_DTL = RadioDtl.class;
	public static final Class CLASS_CHECKBOX_DTL = CheckBoxDtl.class;
}
