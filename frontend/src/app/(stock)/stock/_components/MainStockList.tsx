import {
  AdBox,
  GuideAccordion,
  MoneyInfoElement,
  OtherInfoElement,
} from '@/components';
import FlexBox from '@/components/FlexBox/FlexBox';

export default function MainStockList() {
  return (
    <div className="p-[1.2rem] flex flex-col gap-[1rem]">
      <AdBox
        icon="/images/icon-dollar.png"
        mode={'INTERACTIVE'}
        subText={'자스민은 아래 두 가지 방법으로 관리할 수 있어요!'}
        title={'요술램프 또는 주식 투자로 관리해 보세요'}
      />
      <GuideAccordion
        icon={'/images/icon-bulb.png'}
        title={'요술램프란? 날씨 주식이란?'}
      >
        <div className="text-custom-white">
          <div className="custom-light-text">
            자스민을 관리하는 두가지 방법이 있어요
          </div>
          <div className="mt-4 grid gap-2">
            <div className="custom-medium-text underline underline-offset-4">
              요술램프(파킹통장)
            </div>
            <div className="custom-light-text whitespace-pre">
              <p>주식에 투자하지 않고 요술램프에 보관할 수도 있어요</p>
              <p>보관된 자스민에는 연 2%의 이자가 생겨요</p>
            </div>
          </div>
          <div className="mt-4 grid gap-2">
            <div className="custom-medium-text underline underline-offset-4">
              날씨 주식에 투자하기
            </div>
            <div className="custom-light-text whitespace-pre-line">
              <p>내 주식 가격은</p>
              <p>첫째, 사람들의 구매와 판매에 의해 실시간으로 결정돼요!</p>
              <p>둘째, 매일 주식 시장이 열릴 때 날씨로 인한 변동이 있어요!</p>
            </div>
          </div>
        </div>
      </GuideAccordion>
      <FlexBox
        isDivided={false}
        topChildren={
          <MoneyInfoElement
            imageSrc={'/images/icon-my-chocochips.png'}
            leftExplainText={'내 초코칩 저장소'}
            leftHighlightText={'1,724,900초코칩'}
            buttonOption={'RIGHT_ARROW'}
          />
        }
      />
      <FlexBox
        isDivided={true}
        topChildren={
          <MoneyInfoElement
            imageSrc={'/images/icon-stock.png'}
            leftExplainText={'내 초코칩 저장소'}
            leftHighlightText={'380,700초코칩'}
            buttonOption={'TINY_BUTTON'}
            tinyButtonLabel={'상세'}
          />
        }
        bottomChildren={
          <OtherInfoElement
            imageSrc={'/images/icon-dollar.png'}
            leftExplainText={'2주'}
            leftHighlightText={'우산회사'}
            state={'DOWN'}
            rightExplainText={'-205,400자스민(-53.2%)'}
            rightHighlightText={'181,000초코칩'}
          />
        }
      />
    </div>
  );
}
