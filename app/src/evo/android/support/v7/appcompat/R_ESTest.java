/*
 * This file was automatically generated by EvoSuite
 * Tue Feb 20 22:53:38 GMT 2018
 */

package android.support.v7.appcompat;

import org.junit.Test;
import static org.junit.Assert.*;
import android.support.v7.appcompat.R;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class R_ESTest extends R_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      R.anim r_anim0 = new R.anim();
      assertEquals(2130771975, R.anim.abc_slide_in_top);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      R.attr r_attr0 = new R.attr();
      assertEquals(2130968941, R.attr.tickMarkTint);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      R.integer r_integer0 = new R.integer();
      assertEquals(2131427333, R.integer.config_tooltipAnimTime);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      R.bool r_bool0 = new R.bool();
      assertEquals(2131034115, R.bool.abc_config_closeDialogWhenTouchOutside);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      R.layout r_layout0 = new R.layout();
      assertEquals(2131492916, R.layout.notification_media_cancel_action);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      R.color r_color0 = new R.color();
      assertEquals(2131099649, R.color.abc_background_cache_hint_selector_material_light);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      R r0 = new R();
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      R.style r_style0 = new R.style();
      assertEquals(2131755252, R.style.TextAppearance_AppCompat_Widget_ActionBar_Menu);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      R.dimen r_dimen0 = new R.dimen();
      assertEquals(2131165318, R.dimen.hint_pressed_alpha_material_light);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      R.id r_id0 = new R.id();
      assertEquals(2131361800, R.id.action_bar_activity_content);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      R.string r_string0 = new R.string();
      assertEquals(2131689481, R.string.abc_capital_on);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      R.drawable r_drawable0 = new R.drawable();
      assertEquals(2131230800, R.drawable.abc_text_select_handle_middle_mtrl_light);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      R.styleable r_styleable0 = new R.styleable();
      assertEquals(76, R.styleable.AppCompatTheme_listPreferredItemHeightSmall);
  }
}
