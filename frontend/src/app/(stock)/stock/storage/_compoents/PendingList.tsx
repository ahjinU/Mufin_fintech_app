import { FlexBox, OtherInfoElement } from '@/components';
import { TransactionType } from '../page';
import { commaNum } from '@/utils/commaNum';

export default function PeningList({ list }: { list: TransactionType[] }) {
  return (
    <div className="flex flex-col gap-[0.5rem] py-[0.5rem]">
      {list.map(
        ({ type, transName, date, amount, ratio, cnt, price }, index) => {
          return type === '이자' ? (
            <FlexBox
              mode={'LIST'}
              isDivided={false}
              date={date}
              key={`storage-list-${index}`}
              topChildren={
                <OtherInfoElement
                  leftExplainText={`보관함 이자`}
                  leftHighlightText={`${transName}`}
                  rightExplainText={`(${ratio}%)`}
                  rightHighlightText={`${commaNum(amount)}초코칩`}
                  state={'DOWN'}
                />
              }
            />
          ) : (
            <FlexBox
              mode={'LIST'}
              isDivided={false}
              date={date}
              key={`storage-list-${index}`}
              topChildren={
                <OtherInfoElement
                  leftExplainText={`${type} ${cnt}주`}
                  leftHighlightText={`${transName}`}
                  rightExplainText={`${commaNum(price)}초코칩`}
                  rightHighlightText={`${commaNum(amount)}초코칩`}
                  state={`${type === '매도' ? 'UP' : 'DOWN'}`}
                />
              }
            />
          );
        },
      )}
    </div>
  );
}
