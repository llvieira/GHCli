/*
 * This file was automatically generated by EvoSuite
 * Tue Feb 20 22:55:49 GMT 2018
 */

package android.support.v4;

import org.junit.Test;
import static org.junit.Assert.*;
import android.support.v4.R;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class R_ESTest extends R_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      R.id r_id0 = new R.id();
      assertEquals(2131361808, R.id.action_divider);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      R.styleable r_styleable0 = new R.styleable();
      assertEquals(0, R.styleable.FontFamilyFont_font);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      R.attr r_attr0 = new R.attr();
      assertEquals(2130968737, R.attr.fontProviderQuery);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      R.integer r_integer0 = new R.integer();
      assertEquals(2131427337, R.integer.status_bar_notification_info_maxnum);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      R.string r_string0 = new R.string();
      assertEquals(2131689531, R.string.status_bar_notification_info_overflow);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      R.dimen r_dimen0 = new R.dimen();
      assertEquals(2131165331, R.dimen.notification_right_side_padding_top);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      R.color r_color0 = new R.color();
      assertEquals(2131099725, R.color.notification_action_color_filter);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      R.drawable r_drawable0 = new R.drawable();
      assertEquals(2131230827, R.drawable.notification_bg_normal);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      R.bool r_bool0 = new R.bool();
      assertEquals(2131034112, R.bool.abc_action_bar_embed_tabs);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      R r0 = new R();
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      R.layout r_layout0 = new R.layout();
      assertEquals(2131492923, R.layout.notification_template_lines_media);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      R.style r_style0 = new R.style();
      assertEquals(2131755276, R.style.TextAppearance_Compat_Notification_Media);
  }
}
