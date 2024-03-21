'use client';

import dynamic from 'next/dynamic';
const ApexChart = dynamic(() => import('react-apexcharts'), { ssr: false });
import { series } from './data';

const datetime: 'datetime' = 'datetime';
const pan: 'pan' = 'pan';

const option = {
  chart: {
    height: 290,
    id: 'candles',
    toolbar: {
      autoSelected: pan,
      show: false,
    },
    zoom: {
      enabled: false,
    },
  },
  plotOptions: {
    candlestick: {
      colors: {
        upward: '#3C90EB',
        downward: '#DF7D46',
      },
    },
  },
  xaxis: {
    type: datetime,
  },
};

export function StockCandleChart() {
  return (
    <section className="mx-auto p-[1rem]">
      <ApexChart
        type="candlestick"
        options={option}
        series={series}
        height={300}
        width={310}
      />
    </section>
  );
}
