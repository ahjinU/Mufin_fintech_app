'use client';

import { FlexBox, OtherInfoElement } from '@/components';
import { commaNum } from '@/utils/commaNum';
import { useState, useEffect } from 'react';
import StockChartApis from '../_apis';

export default function StockInfo({ name }: { name: string }) {
  const [companyData, setCompanyData] = useState({
    price: 0,
    incomeRatio: 0,
    transCnt: 0,
    imageUrl: undefined,
    upperLimitPrice: 0,
    lowerLimitPrice: 0,
  });
  const { getStockDetail } = StockChartApis();

  useEffect(() => {
    name &&
      (async function () {
        const data = await getStockDetail(name);
        setCompanyData(data.data);
      })();
  }, []);

  return (
    <FlexBox
      isDivided={false}
      topChildren={
        <OtherInfoElement
          imageSrc={companyData.imageUrl}
          leftHighlightText={`${name} 회사`}
          leftExplainText={`오늘 거래량 ${companyData.transCnt}주`}
          rightHighlightText={`${commaNum(companyData.price)} 초코칩`}
          rightExplainText={`오전 9시보다 ${companyData.incomeRatio}%`}
          state={companyData.incomeRatio >= 0 ? 'UP' : 'DOWN'}
        ></OtherInfoElement>
      }
    />
  );
}
