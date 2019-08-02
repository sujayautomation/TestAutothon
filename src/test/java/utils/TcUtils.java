package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public class TcUtils {
    /**
     * Returns parent DOM object of given WebElement.
     *
     * @param obj         - child object of wanted parent.
     * @param levelsAbove - Parent level.
     * @return Parent object
     */
    public static WebElement getParentElement(@Nonnull WebElement obj, int levelsAbove)
    {
        WebElement parentEle = obj;
        try
        {
            for (int i = 0; i < levelsAbove; i++)
            {
                parentEle = parentEle.findElement(By.xpath(""));
            }

        }
        catch (NullPointerException e)
        {
            //Reporter.("utils.TcUtils.getParentElement: Object ist null, no parent exists!");
        }
        catch (Exception e)
        {
            //TcLog.error("utils.TcUtils.getParentElement: ", e);
        }
        return parentEle;
    }
}
