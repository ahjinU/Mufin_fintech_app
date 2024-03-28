import {
  AdBox,
  GuideAccordion,
  MoneyInfoElement,
  OtherInfoElement,
  FlexBox,
} from '@/components';
import { commaNum } from '@/utils/commaNum';
import useStockStore from '../_store';

export default function MainMyStock() {
  const { myStock, MyParking } = useStockStore.getState();
  const { myStockList, totalPrice } = myStock;

  return (
    <div className="p-[1.5rem] flex flex-col gap-[1rem]">
      <AdBox
        icon="/images/icon-dollar.png"
        mode={'STATIC'}
        subText={'초코칩은 두 가지 방법으로 관리할 수 있어요!'}
        title={'보관함 또는 주식으로 관리해 보세요.'}
      />
      <GuideAccordion
        icon={'/images/icon-bulb.png'}
        title={'초코칩 보관함? 날씨 주식?'}
      >
        <div className="text-custom-white">
          <div className="custom-light-text">
            초코칩을 관리하는 두가지 방법이 있어요.
          </div>
          <div className="mt-4 grid gap-2">
            <div className="custom-medium-text underline underline-offset-4">
              초코칩 보관함 (파킹 통장)
            </div>
            <div className="custom-light-text whitespace-pre">
              <p>주식에 투자하지 않고 보관함에 저장할 수도 있어요.</p>
              <p>보관함에 있는 초코칩에는 연 2%의 이자가 붙어요.</p>
            </div>
          </div>
          <div className="mt-4 grid gap-2">
            <div className="custom-medium-text underline underline-offset-4">
              날씨 주식에 투자하기
            </div>
            <div className="custom-light-text whitespace-pre-line">
              <p>내 주식 가격은</p>
              <p>첫째, 사람들의 구매와 판매에 의해 실시간으로 결정돼요!</p>
              <p>둘째, 매일 시장이 열릴 때 서울 날씨로 인한 변동이 있어요.</p>
            </div>
          </div>
        </div>
      </GuideAccordion>
      <FlexBox
        isDivided={false}
        topChildren={
          <MoneyInfoElement
            imageSrc={'/images/icon-my-chocochips.png'}
            leftExplainText={'내 초코칩 보관함'}
            leftHighlightText={`${commaNum(MyParking.balanceToday)} 초코칩`}
            buttonOption={'RIGHT_ARROW'}
            link="/stock/storage"
          />
        }
      />
      <FlexBox
        isDivided={true}
        topChildren={
          <MoneyInfoElement
            imageSrc={'/images/icon-stock.png'}
            leftExplainText={'내 주식 평가'}
            leftHighlightText={`${commaNum(totalPrice)}초코칩`}
            buttonOption={'TINY_BUTTON'}
            tinyButtonLabel={'상세'}
            link="/stock/list"
          />
        }
        bottomChildren={
          <div className="flex flex-col gap-[1rem]">
            {myStockList?.map(
              ({ cnt, name, incomeRatio, totalPriceCur, income }, index) => {
                return (
                  <OtherInfoElement
                    key={`myStock-${index}`}
                    imageSrc={'/images/icon-dollar.png'}
                    leftExplainText={`${cnt}주`}
                    leftHighlightText={`${name}`}
                    state={`${incomeRatio >= 0 ? 'UP' : 'DOWN'}`}
                    rightExplainText={`${income}초코칩(${incomeRatio}%)`}
                    rightHighlightText={`${commaNum(totalPriceCur)}초코칩`}
                  />
                );
              },
            )}
          </div>
        }
      />
    </div>
  );
}
