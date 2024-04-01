import {
  GuideAccordion,
  FlexBox,
  OtherInfoElement,
  Tab,
  Button,
  Header,
  BackButton,
} from '@/components';
import { StockLineChart } from '../_components/StockLineChart';
import { StockCandleChart } from '../_components/StockCandleChart';
import { StockCall } from '../_components/StockCall';
import { serverPostFetch } from '@/hooks/useServerFetch';
import { commaNum } from '@/utils/commaNum';
import { toCompanyKoreanName, toCompanyEnglishName } from '../_utils';
import Link from 'next/link';

export default async function Company({
  params,
}: {
  params: { name: string };
}) {
  const companyName = toCompanyKoreanName(params.name);

  // 주식 상세 정보 불러오기
  const stockDetail = await serverPostFetch({
    api: '/stock/detail',
    data: { name: companyName },
  });
  const stockDetailData = stockDetail?.data;

  return (
    <>
      <Header>
        <BackButton label={`${companyName} 회사`} />
      </Header>
      <main className="p-[1.2rem] flex flex-col gap-[1rem]">
        <FlexBox
          isDivided={false}
          topChildren={
            <OtherInfoElement
              imageSrc={stockDetailData?.imageUrl}
              leftHighlightText={companyName || ''}
              leftExplainText={`오늘 거래량 ${commaNum(
                stockDetailData?.transCnt,
              )}주`}
              rightExplainText={`${commaNum(stockDetailData?.incomeRatio)}%`}
              rightHighlightText={`${commaNum(stockDetailData?.price)} 초코칩`}
              state="UP"
            ></OtherInfoElement>
          }
        />

        <GuideAccordion
          icon="/images/icon-bulb.png"
          title="주식의 가격은 왜 바뀌어요?"
        >
          <p>
            주식의 가격은 사람들의 구매와 판매에 의해 결정돼요! 많은 사람들이
            주식을 구매하면 가격이 상승하고 반대로, 많은 사람들이 판매하면
            가격이 하락해요. 이로 인해 실시간으로, 지속적으로 주식의 가격은
            변동돼요.
          </p>
        </GuideAccordion>

        <Tab
          tabs={[
            {
              label: '선 차트',
              component: companyName && <StockLineChart name={companyName} />,
            },
            {
              label: '봉 차트',
              component: companyName && <StockCandleChart name={companyName} />,
            },
          ]}
        />

        {companyName && <StockCall companyName={companyName} />}

        {companyName && (
          <Link href={`/company/buy/${toCompanyEnglishName(companyName)}`}>
            <Button label="구매하기" mode="ACTIVE" />
          </Link>
        )}
      </main>
    </>
  );
}
