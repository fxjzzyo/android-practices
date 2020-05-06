package com.example.android_practice;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.example.android_practice.fragment.ParallaxFragment;

/**
 * Created by fanlulin
 * on date 2020/5/4 0004
 */
public class ParallaxLayoutInflater extends LayoutInflater {

    private ParallaxFragment mParallaxFragment;

    public ParallaxLayoutInflater(LayoutInflater original, Context context, ParallaxFragment parallaxFragment) {
        super(context);
        this.mParallaxFragment = parallaxFragment;
        //重新设置布局加载器的工厂
        //工厂：创建布局文件中所有的视图
        setFactory(new ParallaxFactory(this));

    }


    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new ParallaxLayoutInflater(this,newContext,mParallaxFragment);
    }


    class ParallaxFactory implements Factory{
        private LayoutInflater mLayoutInflater;
        private String[] prefixs = {
                "android.widget.",
                "android.view."
        };

        public ParallaxFactory(LayoutInflater inflater) {
            this.mLayoutInflater = inflater;
        }

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            View view = null;

            if (view == null) {
                view = createViewOrFailQuietly(name,context,attrs);
            }

            if (view != null) {
                // 获取自定义属性，通过标签关联的视图上
                setViewTag(view,context,attrs);
                mParallaxFragment.getParallaxViews().add(view);
            }
            return view;
        }

        private void setViewTag(View view, Context context, AttributeSet attrs) {
            // 所有自定义属性
            int attrIds[] = {
              R.attr.a_in,
              R.attr.a_out,
              R.attr.x_in,
              R.attr.x_out,
              R.attr.y_in,
              R.attr.y_out
            };
            // 获取
            TypedArray typedArray = context.obtainStyledAttributes(attrs, attrIds);
            if (typedArray != null && typedArray.length() > 0) {
                ParallaxViewTag parallaxViewTag = new ParallaxViewTag();

                parallaxViewTag.alphaIn = typedArray.getFloat(0,0f);
                parallaxViewTag.alphaOut = typedArray.getFloat(1,0f);
                parallaxViewTag.xIn = typedArray.getFloat(2, 0f);
                parallaxViewTag.xOut = typedArray.getFloat(3, 0f);
                parallaxViewTag.yIn = typedArray.getFloat(4, 0f);
                parallaxViewTag.yOut = typedArray.getFloat(5, 0f);

                view.setTag(R.id.parallax_view_tag,parallaxViewTag);
            }

            typedArray.recycle();
        }

        private View createViewOrFailQuietly(String name, String prefix,Context context, AttributeSet attrs) {
            //通过系统的inflater创建视图，读取系统的属性
            try {
                return mLayoutInflater.createView(name,prefix,attrs);
            } catch (ClassNotFoundException e) {
                return null;
            }

        }

        private View createViewOrFailQuietly(String name, Context context, AttributeSet attrs) {
            //1.自定义控件标签名称带点，所以创建时不需要前缀
            if (name.contains(".")) {
                createViewOrFailQuietly(name,null,context,attrs);
            }
            //2.系统视图需要加上前缀
            for (String prefix:prefixs
                 ) {
                View view = createViewOrFailQuietly(name,prefix,context,attrs);
                if (view != null) {
                    return view;
                }
            }
            return null;
        }
    }
}
