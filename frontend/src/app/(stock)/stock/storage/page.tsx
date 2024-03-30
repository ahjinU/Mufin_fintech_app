import {
  BackButton,
  FlexBox,
  GuideText,
  Header,
  MoneyInfoElement,
  OtherInfoElement,
  Tab,
} from '@/components';
import { commaNum } from '@/utils/commaNum';
import StorageList from './_components/StorageList';
import PendingList from './_components/PendingList';
import { serverPostFetch } from '@/hooks/useServerFetch';

export default async function StockStroage() {
  // 내 파킹 통장 사용 내역 확인
  const myParkingList = await serverPostFetch({
    api: '/parking/history',
  });
  const myParkingListData = myParkingList?.data?.transaction;
  // console.log(myParkingListData);

  // 미체결 주식 내역 확인
  const waitStockList = await serverPostFetch({
    api: '/stock/order/wait',
  });
  const waitStockListData = waitStockList?.data?.transaction;
  // console.log(waitStockListData);

  // 내 초코칩 보관함 정보 들고 오기
  const myParking = await serverPostFetch({ api: '/parking/account' });
  const { balanceToday, ratio, balanceTmrw, interest } = myParking?.data;

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
              leftHighlightText={`${commaNum(balanceToday)} 초코칩`}
              buttonOption={'RATE'}
            />
          }
          bottomChildren={
            <OtherInfoElement
              leftExplainText={`이자율은 ${ratio}%`}
              leftHighlightText={`내일 갖게 될 초코칩`}
              rightHighlightText={`${commaNum(balanceTmrw)} 초코칩`}
              rightExplainText={`+${interest} 초코칩`}
              state={'DOWN'}
            />
          }
        />
        <div>
          <GuideText
            text={'보관함 전체 내역과 아직 체결되지 않은 내역을 볼 수 있어요!'}
          />
          <Tab
            tabs={[
              {
                label: '보관함 내역',
                component: <StorageList list={myParkingListData} />,
              },
              {
                label: '미체결 주식',
                component: <PendingList list={waitStockListData} />,
              },
            ]}
          />
        </div>
      </div>
    </div>
  );
}
