import {
  GuideAccordion,
  FlexBox,
  OtherInfoElement,
  Tab,
  Button,
} from '@/components';
import { StockLineChart } from './_components/StockLineChart';
import { StockCandleChart } from './_components/StockCandleChart';
import { StockCall } from './_components/StockCall';

export default function Company() {
  return (
    <main className="p-[1.2rem] flex flex-col gap-[1rem]">
      <FlexBox
        isDivided={false}
        topChildren={
          <OtherInfoElement
            imageSrc="/images/icon-dollar.png"
            leftHighlightText="바람막이 회사"
            leftExplainText="거래량 479주"
            rightHighlightText="871,030 자스민"
            rightExplainText="+44.8%"
            state="UP"
          ></OtherInfoElement>
        }
      />

      <GuideAccordion
        icon="/images/icon-bulb.png"
        title="주식의 가격은 왜 바뀌어요?"
        children={
          <p>
            주식의 가격은 사람들의 구매와 판매에 의해 결정돼요! 많은 사람들이
            주식을 구매하면 가격이 상승하고 반대로, 많은 사람들이 판매하면
            가격이 하락해요. 이로 인해 실시간으로, 지속적으로 주식의 가격은
            변동돼요.
          </p>
        }
      />

      <Tab
        tabs={[
          { label: '선 차트', component: <StockLineChart /> },
          { label: '봉 차트', component: <StockCandleChart /> },
        ]}
      />

      <StockCall />

      <Button label="구매하기" mode="ACTIVE" />
    </main>
  );
}
