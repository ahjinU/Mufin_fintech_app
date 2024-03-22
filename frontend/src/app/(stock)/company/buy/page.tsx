import {
  FlexBox,
  MoneyInfoElement,
  OtherInfoElement,
  Button,
} from '@/components';
import { StockBuySell } from '../_components/StockBuySell';

export default async function Buy() {
  return (
    <main className="p-[1.2rem] flex flex-col gap-[1rem]">
      <FlexBox
        isDivided={false}
        topChildren={
          <MoneyInfoElement
            imageSrc="/images/icon-dollar.png"
            leftHighlightText="1,724,900 자스민"
            leftExplainText="내 초코칩 보관함"
            buttonOption="RIGHT_ARROW"
          ></MoneyInfoElement>
        }
      />

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

      <StockBuySell mode="BUY" />

      <Button mode="ACTIVE" label="확인" />
    </main>
  );
}
