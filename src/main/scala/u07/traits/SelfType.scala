package u07.traits

trait Producer:
  def get(): String

// not a subtype of Producer, but truly a mixin!
trait ProducerUtils:
  producer: Producer =>
  def getMany(n: Int): Seq[String] =
     for i <- 0 to n yield producer.get()

class RandomProducer extends Producer:
  override def get(): String = Math.random().toString

class ConstantProducer(s:String) extends Producer:
  override def get(): String = s

class FullRandomProducer extends RandomProducer with ProducerUtils

@main def tryProducers =
  // attaching utils to any Producer
  val producer = new FullRandomProducer
  val producer2 = new RandomProducer() with ProducerUtils
  val producer3 = new ConstantProducer("a") with ProducerUtils

  println( producer.getMany(5) )
  println( producer2.getMany(5) )
  println( producer3.getMany(5) )
