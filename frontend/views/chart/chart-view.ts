import {Chart} from 'chart.js';
// @ts-ignore
import {
  LitElement,customElement,TemplateResult,html,property
} from "lit-element";
// @ts-ignore
@customElement('chart-view')
export class chartView extends LitElement {
   @property() jsonChart : string = "";
   // @ts-ignore
   private chart : Chart;


  constructor() {
      super();
   }


     public async updated() {
         this.createNewChart();
       }

     createNewChart(){
         // @ts-ignore
        const canvas = this.shadowRoot.querySelector('canvas');
         // @ts-ignore
        const ctx = canvas.getContext('2d')!;
         // @ts-ignore
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        if(this.chart!=null){
            this.chart.destroy();
        }
        // @ts-ignore
      this.chart = new Chart(ctx,  JSON.parse(this.jsonChart));
     }


    /**
         * Use lit-html render Elements
         */
        public render(): void|TemplateResult {
            return html`
                <style>
                    .chart-size{
                        position: relative;
                    }

                </style>
                <div class="chart-size" style="position: relative; height:80vh; width:80vw">
                    <canvas></canvas>
                </div>
            `;
        }


}