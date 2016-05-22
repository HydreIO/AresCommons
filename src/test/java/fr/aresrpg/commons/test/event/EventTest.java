package fr.aresrpg.commons.test.event;

import fr.aresrpg.commons.event.Event;
import fr.aresrpg.commons.event.EventBus;
import org.junit.Assert;
import org.junit.Test;

public class EventTest {
	public static final int COUNT = 50;

	public static class PriorityTestEvent implements Event<PriorityTestEvent>{
		public static final EventBus<PriorityTestEvent> BUS = new EventBus<>(PriorityTestEvent.class);

		public int counter = 0;

		public void called(int index){
			Assert.assertEquals(counter, index);
			counter++;
		}
		@Override
		public EventBus<PriorityTestEvent> getBus() {
			return BUS;
		}

		@Override
		public boolean isAsynchronous() {
			return false;
		}
	}

	@Test
	public void priorityTestEvent(){
		for(int i = 0 ; i < COUNT ; i++){
			final int y = i;
			EventBus.getBus(PriorityTestEvent.class).subscribe(e -> e.called(y) , y);
		}
		new PriorityTestEvent().send();
	}

	public static class AsynchronousTestEvent implements Event<AsynchronousTestEvent>{
		public static final EventBus<AsynchronousTestEvent> BUS = new EventBus<>(AsynchronousTestEvent.class);

		public Thread startThread;

		public AsynchronousTestEvent(Thread startThread) {
			this.startThread = startThread;
		}

		public void called(){
			Assert.assertNotEquals("Called from start thread" , startThread , Thread.currentThread());
		}
		@Override
		public EventBus<AsynchronousTestEvent> getBus() {
			return BUS;
		}

		@Override
		public boolean isAsynchronous() {
			return true;
		}
	}

	@Test
	public void asynchronousTestEvent(){
		for(int i = 0 ; i < COUNT ; i++){
			EventBus.getBus(AsynchronousTestEvent.class).subscribe(AsynchronousTestEvent::called , i);
		}
		new AsynchronousTestEvent(Thread.currentThread()).send();
	}

	public static class SynchronousTestEvent implements Event<SynchronousTestEvent>{
		public static final EventBus<SynchronousTestEvent> BUS = new EventBus<>(SynchronousTestEvent.class);

		public Thread startThread;

		public SynchronousTestEvent(Thread startThread) {
			this.startThread = startThread;
		}

		public void called(){
			Assert.assertEquals("Called from other thread than start thread",startThread , Thread.currentThread());
		}
		@Override
		public EventBus<SynchronousTestEvent> getBus() {
			return BUS;
		}

		@Override
		public boolean isAsynchronous() {
			return false;
		}
	}

	@Test
	public void synchronousTestEvent(){
		for(int i = 0 ; i < COUNT ; i++){
			EventBus.getBus(SynchronousTestEvent.class).subscribe(SynchronousTestEvent::called , i);
		}
		new SynchronousTestEvent(Thread.currentThread()).send();
	}

	public static class TestEvent implements Event<TestEvent>{
		public static final EventBus<TestEvent> BUS = new EventBus<>(TestEvent.class);


		@Override
		public EventBus<TestEvent> getBus() {
			return BUS;
		}

		@Override
		public boolean isAsynchronous() {
			return false;
		}
	}

	@Test
	public void staticMethodTestEvent() throws Throwable {
		EventBus.getBus(TestEvent.class).subscribeMethod(EventTest.class.getMethod("staticTestMethod" , TestEvent.class) , null, 1);
		new TestEvent().send();
	}

	public static void staticTestMethod(TestEvent event){
		Assert.assertTrue("Method executed" , true);
	}

	@Test
	public void methodTestEvent() throws Throwable {
		EventBus.getBus(TestEvent.class).subscribeMethod(EventTest.class.getMethod("testMethod" , TestEvent.class) , this, 1);
		new TestEvent().send();
	}

	public void testMethod(TestEvent event){
		Assert.assertTrue("Method executed" , true);
	}

}
