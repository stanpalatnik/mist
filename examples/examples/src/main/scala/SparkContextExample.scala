import mist.api.all._
import mist.api.encoding.DefaultExtractorInstances._
import mist.api.encoding.DefaultEncoderInstances._
import org.apache.spark.SparkContext

object SparkContextExample extends MistFn with Logging {

  override def handle: Handle = {
    withArgs(
      arg[Seq[Int]]("numbers"),
      arg[Int]("multiplier", 2)
    ).withMistExtras
     .onSparkContext((nums: Seq[Int], mult: Int, extras: MistExtras, sc: SparkContext) => {
       import extras._
       logger.info(s"Heello from $jobId")
       sc.parallelize(nums).map(x => {logger.info("yoo"); x * mult}).collect()
     })
  }

}
