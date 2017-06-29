
一、简介
谷歌开发

二、可以做什么

1、自动管理APP的生命周期:Activity,Fragment，而不会出现内存泄露

2、数据持久化的支持，能够很方便地对 UI 加载数据

三、构建模块

1、LifeCycle
	Lifecycle, LifecycleOwner 和 LifecycleObserver

	public interface LifecycleOwner{
		Lifecycle getLifecycle();
	}

	public interface LifecycleRegistryOwner extends LifecycleOwner{
		LifecycleRegistry getLifecycle();
	}

	class BaseLifecycleActivity extends AppCompatActivity implements LifecycleOwner{
		val regist = LifecycleRegistry(this);
	}


2、LiveData
	持有一些数据并且实现了观察者模式

	优点
	.能够避免内存泄露
	.当结束Activity的时候不会出现crash
	.能够实时更新数据
	.当Activity或Fragment重新创建时能够立马恢复数据，比如横竖屏切换
	.共享资源
	.不需要手动维护生命周期

	数据转换，和RXJava的使用方法一样
	Transformations.map()
	Transformations.switchMap()

	LiveDataSampleViewModel extends ViewModel{
	 	MutableLiveData<Long> mElapsedTime = new MutableLiveData<>();

	 	getElapsedTime(){
	 		return mElapsedTime;
	 	}

	 	public LiveDataSampleViewModel(){
	 		new Thread(){
	 			public void run(){ mElapsedTime.setValue(time); }
	 		}
	 	}

	}

	SampleActivity extends LifecycleActivity{
		LiveDataSampleViewModel liveDataSampleModel;
		onCreate(){
			liveDataSampleModel = ViewModelProvider.of(this).get(LiveDataSampleViewModel.class);
			subscribe();
		}

		void subscribe(){
			Observer<Long> mObserver = (aLong)->{
				setText(aLong);//update view
			}
			liveDataSampleModel.getElapsedTime().observe(this,mObserver);
		}
	}

3、ViewModel

	存储管理 UI 数据以便 UI 的配置状态改变的时候能够实时地回复数据。

	特点:
	1、当屏幕切换或改变时能够自动保留数据
	2、异步调用
	3、为UI持有数据

	SampleViewModel extends ViewModel{
		long data;
		public void setData(long data){
			this.data = data;
		}
		public long getData(){
			return data;
		}
	}

	SampleActivity extends LifecycleActivity{
		onCreate(){
			SampleViewModel sampleModel = ViewModelProvider.of(this).get(SampleViewModel.class);
			sampleModel.setData();
			sampleModel.getData();
		}

	}

	LifecycleActivity extends FragmentActivity implements LifecycleRegistryOwner

	interface LifecycleRegistry extends LifecycleOwner{}

	interface LifecycleOwner{
		Lifecycle getLifecycle();
	}

	ViewModel Lifecycle -->// https://codelabs.developers.google.com/codelabs/android-lifecycles/img/8efa40941430fc4c.png

	Activity 或 Fragment 使用观察者模式实时更新数据 ---> 2、LiveData


4、Rome Persistence Library，数据的持久化
	
	提供对SQLite的抽象层，并且能够流畅地操作SQLite数据库

	Room 库，包括 Database, Entity, 和 DAO 三个主要模块

	@Database @Entity @Dao

	创建数据库：
	RoomDatabase database = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, DATABASE_NAME).build();

	@Entity
		->@Ignore:默认对每个属性添加进数据库表的字段，如果不需要的话可以加上这个
		->@PrimaryKey 必须要有，多个的情况 @Entity(primaryKeys = {"firstName", "lastName"})

	@Dao
		->@Insert