import type { Meta, StoryObj } from '@storybook/react';
import BottomSheet from './BottomSheet';

const meta = {
  title: 'Common/BottomSheet',
  component: BottomSheet,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    size: {
      description: '바텀 시트(화면 아래에서 올라오는 UI)의 크기입니다.',
    },
    title: {
      description: '바텀 시트의 제목입니다.',
    },
    content: {
      description:
        '제목에 대한 부연 설명으로 크기가 SMALL인 경우는 해당하지 않습니다.',
    },
    imageSrc: {
      description: '바텀 시트 중앙에 들어갈 이미지입니다.',
    },
    isXButtonVisible: {
      description: '우측 상단 X 버튼의 여부를 지정합니다.',
    },
  },
} satisfies Meta<typeof BottomSheet>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Small: Story = {
  args: {
    size: 'SMALL',
    title: '안전하게 송금하는 중이에요',
    imageSrc: 'http://localhost:3000/images/icon-silver-coin.png',
    isXButtonVisible: false,
    isOpen: true,
  },
};

export const Medium: Story = {
  args: {
    size: 'MEDIUM',
    title: '계좌 사용이 정지되었습니다.',
    content:
      '결제 비밀번호 입력에 5회 실패해서 계좌 사용이 정지되었습니다. 고객 센터로 문의해주세요.',
    imageSrc: 'http://localhost:3000/images/icon-warning.png',
    isXButtonVisible: true,
    isOpen: true,
  },
};
