import {
  BackButton,
  FlexBox,
  GuideText,
  Header,
  MoneyInfoElement,
  OtherInfoElement,
} from '@/components';
import { commaNum } from '@/utils/commaNum';
import useStockStore from '../_store';
import { getParkingAccount } from '../_apis';

export default async function StockStroage() {
  const { MyParking } = useStockStore.getState();

  const { balanceToday, ratio, balanceTmrw } = MyParking;

  const storageRes = await getParkingAccount();
  // const storageList = storageRes?.data.transaction;
  return (
    <div>
      <Header>
        <BackButton label={'내 초코칩 보관함'} />
      </Header>
      <div className="flex flex-col px-[1rem] gap-[1rem]">
        <FlexBox
          isDivided={true}
          topChildren={
            <MoneyInfoElement
              imageSrc={'/images/icon-my-chocochips.png'}
              leftExplainText={'내 초코칩 보관함'}
              leftHighlightText={`${commaNum(balanceToday)}초코칩`}
              buttonOption={'RATE'}
            />
          }
          bottomChildren={
            <OtherInfoElement
              leftExplainText={`이자로 ${ratio}%를 더 받아요`}
              leftHighlightText={`내일 내 초코칩`}
              rightHighlightText={`${commaNum(balanceTmrw)}초코칩`}
              rightExplainText={`+${ratio}초코칩`}
              state={'DOWN'}
            />
          }
        />
        <div>
          <GuideText
            text={'보관함 전체 내역과 아직 체결되지 않은 내역을 볼 수 있어요!'}
          />
          {/* <Tab
              tabs={[
                {
                  label: '보관함 내역',
                  component: <StorageList list={storageList} />,
                },
                {
                  label: '미체결 주식',
                  component: <PendingList list={storageList} />,
                },
              ]}
            /> */}
        </div>
      </div>
    </div>
  );
}