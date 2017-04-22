#MVP
![MacDown logo](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492861479429&di=f79650fd450f67d4485a828034d2b567&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F1e30e924b899a9012050c1f01f950a7b0308f58b.jpg)

## MVC模式

MVC模式的结构分为三部分，实体层的Model，视图层的View，以及控制层的Controller。
![MacDown logo](https://segmentfault.com/image?src=http://7xih5c.com1.z0.glb.clouddn.com/15-10-11/13126761.jpg&objectId=1190000003927200&token=9cdd1d129e9862fa016f2c48560187c9)

* 其中View层其实就是程序的UI界面，用于向用户展示数据以及接收用户的输入
* 而Model层就是JavaBean实体类，用于保存实例数据
* Controller控制器用于更新UI界面和数据实例

例如，View层接受用户的输入，然后通过Controller修改对应的Model实例；同时，当Model实例的数据发生变化的时候，需要修改UI界面，可以通过Controller更新界面。（View层也可以直接更新Model实例的数据，而不用每次都通过Controller，这样对于一些简单的数据更新工作会变得方便许多。）


## MVP模式

在Android项目中，Activity和Fragment占据了大部分的开发工作。如果有一种设计模式（或者说代码结构）专门是为优化Activity和Fragment的代码而产生的，你说这种模式重要不？这就是MVP设计模式。

按照MVC的分层，Activity和Fragment（后面只说Activity）应该属于View层，用于展示UI界面，以及接收用户的输入，此外还要承担一些生命周期的工作。Activity是在Android开发中充当非常重要的角色，特别是TA的生命周期的功能，所以开发的时候我们经常把一些业务逻辑直接写在Activity里面，这非常直观方便，代价就是Activity会越来越臃肿，超过1000行代码是常有的事，而且如果是一些可以通用的业务逻辑（比如用户登录），写在具体的Activity里就意味着这个逻辑不能复用了。如果有进行代码重构经验的人，看到1000+行的类肯定会有所顾虑。因此，Activity不仅承担了View的角色，还承担了一部分的Controller角色，这样一来V和C就耦合在一起了，虽然这样写方便，但是如果业务调整的话，要维护起来就难了，而且在一个臃肿的Activity类查找业务逻辑的代码也会非常蛋疼，所以看起来有必要在Activity中，把View和Controller抽离开来，而这就是MVP模式的工作了。

![MacDown logo](https://segmentfault.com/image?src=http://7xih5c.com1.z0.glb.clouddn.com/15-10-11/2114527.jpg&objectId=1190000003927200&token=090ab9129b52d861300a716ee4d9180c/view)

MVP模式的核心思想：

***MVP把Activity中的UI逻辑抽象成View接口，把业务逻辑抽象成Presenter接口，Model类还是原来的Model***。

这就是MVP模式，现在这样的话，Activity的工作的简单了，只用来响应生命周期，其他工作都丢到Presenter中去完成。从上图可以看出，Presenter是Model和View之间的桥梁，为了让结构变得更加简单，View并不能直接对Model进行操作，这也是MVP与MVC最大的不同之处。

## 使用方法
将Activity看做是View层，需要实现MainView接口,MainView需要继承BaseView接口。

	public interface MainView extends BaseView {
	
	    void onLoad(String loading);
	
	    void onError(String error);
	}
	
业务逻辑层放在Presenter当中，例如MainPresenter，需要直接继承BasePresenter。


	public class MainPresenter extends BasePresenter<MainView> {
	
	    public MainPresenter(@Nullable MainView view) {
	        super(view);
	    }
	
	    public void getData() {
	        getView().onLoad("loading");
	        getView().onError("error");
	    }
	}
	
## 例子
	package com.jiangwei.mvp;
	
	import com.jiangwei.mvplib.MvpActivity;
	
	import android.os.Bundle;
	import android.view.View;
	import android.widget.Button;
	import android.widget.Toast;
	
	public class MainActivity extends MvpActivity<MainPresenter> implements MainView, View.OnClickListener {
	    private Button mBtn;
	
	    @Override
	    protected MainPresenter createPresenter() {
	        return new MainPresenter(this);
	    }
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        mBtn = (Button) findViewById(R.id.btn);
	        mBtn.setOnClickListener(this);
	    }
	
	    @Override
	    public void onLoad(String loading) {
	        Toast.makeText(MainActivity.this, "onLoading", Toast.LENGTH_SHORT).show();
	    }
	
	    @Override
	    public void onError(String error) {
	        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
	    }
	
	    @Override
	    public void onClick(View v) {
	        if (v.getId() == R.id.btn) {
	            getPresenter().getData();
	        }
	    }
	}
