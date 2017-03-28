/**
 * Created by Medxi on 2017/3/24.
 */

export class ExecuteJob {
  constructor(
    public className: string,
    public jobGroup: string,
    public cronExpression: string,
    public description: string
  ){}

  public static defaultExecuteJob:ExecuteJob = new ExecuteJob('','','','');
}
