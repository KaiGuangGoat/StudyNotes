
一、简介
谷歌开发

二、可以做什么

1、自动管理APP的生命周期:Activity,Fragment，而不会出现内存泄露

2、数据持久化的支持，能够很方便地对 UI 加载数据

三、构建模块

1、LifeCycle
	Lifecycle, LifecycleOwner 和 LifecycleObserver


2、LiveData
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

3、ViewModel

4、DataPersistence，数据的持久化
	Room 库，包括 Database, Entity, 和 DAO 三个主要模块

