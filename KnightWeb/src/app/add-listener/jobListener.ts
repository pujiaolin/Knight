/**
 * Created by Medxi on 2017/3/28.
 */

export class JobListener {
  constructor(
    public masterJobClassName:string,
    public minorJobClassName:string,
    public masterJobGroup:string
  ){}

  public static defaultListener:JobListener = new JobListener('','','');
}
