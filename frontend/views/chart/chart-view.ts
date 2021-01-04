import {Chart} from 'chart.js';
// @ts-ignore
import {
  LitElement,customElement,TemplateResult,html
} from "lit-element";

@customElement('chart-view')
export class chartView extends LitElement {


  constructor() {
      super();

    }

    public firstUpdated(): void {
                // @ts-ignore
                const ctx: CanvasRenderingContext2D = this.shadowRoot
                .querySelector('canvas')
                .getContext('2d');
                 new Chart(ctx, {
                                 // The type of chart we want to create
                                 type: 'line',

                                 // The data for our dataset
                                 data: {
                                     labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
                                     datasets: [{
                                         label: 'My First dataset',
                                         backgroundColor: 'rgb(255, 99, 132)',
                                         borderColor: 'rgb(255, 99, 132)',
                                         data: [0, 10,20, 30, 20, 10, 0]
                                     }]
                                 },

                                 // Configuration options go here
                                 options: {}
                             });

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