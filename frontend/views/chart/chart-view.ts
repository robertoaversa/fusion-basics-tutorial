import {Chart} from 'chart.js';
// @ts-ignore
import {
  LitElement,customElement,TemplateResult,html
} from "lit-element";
// @ts-ignore
import ChartJson from "../../generated/com/example/application/views/chart/ChartJson";
import { getChart } from "../../generated/ChartEndpoint";
import ChartJsonModel from "../../generated/com/example/application/views/chart/ChartJsonModel";
import { Binder} from "@vaadin/form";
@customElement('chart-view')
export class chartView extends LitElement {
   private binder = new Binder(this, ChartJsonModel);

  constructor() {
      super();
    }


   public async firstUpdated(arg: any){
    super.firstUpdated(arg);
    this.binder.read(await getChart());
    var test = this.binder.value.jsonChart;
                // @ts-ignore
                const ctx: CanvasRenderingContext2D = this.shadowRoot
                .querySelector('canvas')
                .getContext('2d');
                 new Chart(ctx,  JSON.parse(test));
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
                    canvas{
                        width:400px;
                        height:400px;
                    }
                </style>
                <div class="chart-size">
                    <canvas></canvas>
                </div>
            `;
        }


}