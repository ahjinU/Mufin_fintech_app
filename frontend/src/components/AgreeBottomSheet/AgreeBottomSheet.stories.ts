import type { Meta, StoryObj } from '@storybook/react';
import AgreeBottomSheet from './AgreeBottomSheet';

const meta = {
  title: 'Common/AgreeBottomSheet',
  component: AgreeBottomSheet,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    title: {
      description: '동의 버튼이 있는 바텀 시트의 제목입니다.',
    },
    notice: {
      description: '동의할 내용에 대한 안내 텍스트입니다.',
    },
    conditions: {
      description: '동의할 조건에 대한 문자열 배열(Array)을 전달합니다.',
    },
    isOpen: {
      description:
        '현재 열려있는지에 따라 다른 애니메이션 효과를 구현하기 위해 prop으로 전달합니다.',
    },
    handleClickCloseButton: {
      description:
        '(옵션)상위 컴포넌트에서 전달되는 바텀 시트 닫기 제어 함수(setIsOpen)를 할당합니다.',
    },
    handleClickConfirmButton: {
      description:
        '(옵션)상위 컴포넌트에서 전달되는 바텀 시트에서 발생한 데이터 제어 함수(setData)를 할당합니다.',
    },
  },
} satisfies Meta<typeof AgreeBottomSheet>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    title: '적금 상품 가입 동의',
    notice: '적금 상품에 가입하려면 아래 약관 동의가 필요해요.',
    conditions: ['개인 정보 수집 및 이용 동의', '적금 상품 가입 동의'],
    isOpen: false,
  },
};
