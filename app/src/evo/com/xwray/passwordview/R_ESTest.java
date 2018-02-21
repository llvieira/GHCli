/*
 * This file was automatically generated by EvoSuite
 * Tue Feb 20 22:51:58 GMT 2018
 */

package com.xwray.passwordview;

import org.junit.Test;
import static org.junit.Assert.*;
import com.xwray.passwordview.R;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class R_ESTest extends R_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      R.style r_style0 = new R.style();
      assertEquals(2131755221, R.style.TextAppearance_AppCompat_Display4);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      R.styleable r_styleable0 = new R.styleable();
      assertEquals(20, R.styleable.ActionBar_logo);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      R.id r_id0 = new R.id();
      assertEquals(2131361911, R.id.normal);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      R.anim r_anim0 = new R.anim();
      assertEquals(2130771972, R.anim.abc_popup_exit);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      R.attr r_attr0 = new R.attr();
      assertEquals(2130968843, R.attr.overlapAnchor);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      R.drawable r_drawable0 = new R.drawable();
      assertEquals(2131230752, R.drawable.abc_ic_menu_overflow_material);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      R.string r_string0 = new R.string();
      assertEquals(2131689502, R.string.abc_toolbar_collapse_description);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      R.layout r_layout0 = new R.layout();
      assertEquals(2131492924, R.layout.notification_template_media);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      R r0 = new R();
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      R.bool r_bool0 = new R.bool();
      assertEquals(2131034113, R.bool.abc_allow_stacked_button_bar);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      R.color r_color0 = new R.color();
      assertEquals(2131099682, R.color.bright_foreground_material_dark);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      R.dimen r_dimen0 = new R.dimen();
      assertEquals(2131165257, R.dimen.abc_text_size_title_material_toolbar);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      R.integer r_integer0 = new R.integer();
      assertEquals(2131427337, R.integer.status_bar_notification_info_maxnum);
  }
}
